package com.springproj.schedulebuilder.repository.daos;

import com.springproj.schedulebuilder.model.dto.slot.SlotCreationDto;
import com.springproj.schedulebuilder.model.dto.slot.SlotUpdateDto;
import com.springproj.schedulebuilder.repository.queries.SlotQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

@Repository
public class SlotDaoImpl {
    private final SlotQueries slotQueries;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SlotDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.slotQueries = new SlotQueries();
    }

    @Transactional
    public void save(SlotCreationDto slot) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement(slotQueries.getCreateSlot, Statement.RETURN_GENERATED_KEYS);
            statement.setBoolean(1, slot.getLection());
            statement.setString(2, slot.getRoom());
            statement.setInt(3, slot.getDay());
            statement.setInt(4, slot.getTime());
            return statement;
        }, holder);

        int primaryKey = Objects.requireNonNull(holder.getKey()).intValue();

        slot.weeks.forEach(week -> {
            jdbcTemplate.update(con -> {
                PreparedStatement statement = con.prepareStatement(slotQueries.getCreateSlotSubject, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, slot.subject);
                statement.setInt(2, primaryKey);
                statement.setInt(3, week);
                return statement;
            });
        });
    }

    @Transactional
    public void update(SlotUpdateDto slot) {
        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement(slotQueries.getUpdateSlot, Statement.NO_GENERATED_KEYS);
            statement.setBoolean(1, slot.getLection());
            statement.setString(2, slot.getRoom());
            statement.setInt(3, slot.getDay());
            statement.setInt(4, slot.getTime());
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
                return statement;
            });
        });
    }

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
