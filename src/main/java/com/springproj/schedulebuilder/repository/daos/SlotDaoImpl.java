package com.springproj.schedulebuilder.repository.daos;

import com.springproj.schedulebuilder.exception.BadRequestException;
import com.springproj.schedulebuilder.model.domain.slot.Slot;
import com.springproj.schedulebuilder.model.domain.slot.SubjectSlots;
import com.springproj.schedulebuilder.model.dto.slot.SlotCreationDto;
import com.springproj.schedulebuilder.model.dto.slot.SlotUpdateDto;
import com.springproj.schedulebuilder.repository.DaysRepository;
import com.springproj.schedulebuilder.repository.IntervalsRepository;
import com.springproj.schedulebuilder.repository.SlotDao;
import com.springproj.schedulebuilder.repository.queries.SlotQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class SlotDaoImpl implements SlotDao {
    private final SlotQueries slotQueries;
    private final JdbcTemplate jdbcTemplate;
    private final DaysRepository daysRepository;
    private final IntervalsRepository intervalsRepository;

    @Autowired
    public SlotDaoImpl(
            JdbcTemplate jdbcTemplate,
            DaysRepository daysRepository,
            IntervalsRepository intervalsRepository
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.slotQueries = new SlotQueries();
        this.daysRepository = daysRepository;
        this.intervalsRepository = intervalsRepository;
    }

    @Override
    public Slot findById(Integer id, Integer user_id) throws BadRequestException {
        var slot = jdbcTemplate.queryForObject(slotQueries.getSlotById, new Object[]{id}, (resultSet, i) -> {
            var day = daysRepository.findById(resultSet.getInt("day_id"));
            var interval = intervalsRepository.findById(resultSet.getInt("time_id"));
            return Slot.builder()
                    .id(resultSet.getInt("id"))
                    .day(day.get())
                    .time(interval.get())
                    .room(resultSet.getString("room"))
                    .lection(resultSet.getBoolean("lection"))
                    .user_id(resultSet.getInt("user_id"))
                    .build();
        });
        assert slot != null;
        if (!Objects.equals(slot.getUser_id(), user_id)) {
            throw new BadRequestException("This user doesn't own this slot");
        }
        var subjectSlots = jdbcTemplate.query(slotQueries.getSubjectSlotsBySlotId, new Object[]{id}, ((resultSet, i) -> {
            return SubjectSlots.builder()
                    .week(resultSet.getInt("week"))
                    .id(resultSet.getInt("id"))
                    .user_id(resultSet.getInt("user_id"))
                    .build();
        }));
        slot.setSubjectSlots(subjectSlots);
        return slot;
    }

    @Override
    public List<Slot> findByUserSubject(Integer subjectId, Integer userId) {
        var ids = jdbcTemplate.queryForList(slotQueries.getSlotsBySubject, new Object[]{subjectId, userId}, Integer.class);
        List<Slot> result = new ArrayList<>();
        ids.forEach( i -> {
            try {
                result.add(findById(i, userId));
            } catch (BadRequestException e) {
                e.printStackTrace();
            }
        });
        return result;
    }

    @Override
    @Transactional
    public void save(SlotCreationDto slot, Integer user_id) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement(slotQueries.getCreateSlot, Statement.RETURN_GENERATED_KEYS);
            statement.setBoolean(1, slot.getLection());
            statement.setString(2, slot.getRoom());
            statement.setInt(3, slot.getDay());
            statement.setInt(4, slot.getTime());
            statement.setInt(5, user_id);
            return statement;
        }, holder);

        int primaryKey = Objects.requireNonNull(holder.getKey()).intValue();

        slot.weeks.forEach(week -> {
            jdbcTemplate.update(con -> {
                PreparedStatement statement = con.prepareStatement(slotQueries.getCreateSlotSubject, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, slot.subject);
                statement.setInt(2, primaryKey);
                statement.setInt(3, week);
                statement.setInt(4, user_id);
                return statement;
            });
        });
    }

    @Override
    @Transactional
    public void update(SlotUpdateDto slot) {
        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement(slotQueries.getUpdateSlot, Statement.NO_GENERATED_KEYS);
            statement.setBoolean(1, slot.getLection());
            statement.setString(2, slot.getRoom());
            statement.setInt(3, slot.getDay());
            statement.setInt(4, slot.getTime());
            statement.setInt(5, slot.getUser_id());
            statement.setInt(6, slot.getId());
            return statement;
        });

        jdbcTemplate.update(con -> {
           PreparedStatement statement = con.prepareStatement(slotQueries.deleteAllSubjectSlots, Statement.NO_GENERATED_KEYS);
           statement.setInt(1, slot.getId());
           return statement;
        });

        slot.weeks.forEach(week -> {
            jdbcTemplate.update(con -> {
                PreparedStatement statement = con.prepareStatement(slotQueries.getCreateSlotSubject, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, slot.getSubject_id());
                statement.setInt(2, slot.getId());
                statement.setInt(3, week);
                statement.setInt(4, slot.getUser_id());
                return statement;
            });
        });
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement(slotQueries.deleteAllSubjectSlots, Statement.NO_GENERATED_KEYS);
            statement.setInt(1, id);
            return statement;
        });

        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement(slotQueries.deleteSlot, Statement.NO_GENERATED_KEYS);
            statement.setInt(1, id);
            return statement;
        });
    }
}
