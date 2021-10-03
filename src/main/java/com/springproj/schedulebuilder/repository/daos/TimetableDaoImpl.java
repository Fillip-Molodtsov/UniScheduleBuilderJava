package com.springproj.schedulebuilder.repository.daos;

import com.springproj.schedulebuilder.exception.BadRequestException;
import com.springproj.schedulebuilder.model.domain.slot.Slot;
import com.springproj.schedulebuilder.model.domain.slot.SubjectSlots;
import com.springproj.schedulebuilder.model.domain.user.AppUser;
import com.springproj.schedulebuilder.model.dto.timetable.Timetable;
import com.springproj.schedulebuilder.repository.SlotRepository;
import com.springproj.schedulebuilder.repository.SubjectRepository;
import com.springproj.schedulebuilder.repository.queries.TimetableQueries;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TimetableDaoImpl {
    private final JdbcTemplate jdbcTemplate;
    private final TimetableQueries timetableQueries;
    private final SlotRepository slotRepository;
    private final SubjectRepository subjectRepository;
    private final SlotDaoImpl slotDao;

    TimetableDaoImpl(
            JdbcTemplate jdbcTemplate,
            SlotRepository slotRepository,
            SubjectRepository subjectRepository,
            SlotDaoImpl slotDao
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.slotRepository = slotRepository;
        this.slotDao = slotDao;
        this.subjectRepository = subjectRepository;
        this.timetableQueries = new TimetableQueries();
    }

    public List<SubjectSlots> getSubjectSlots(Integer user_id) {
        return jdbcTemplate.query(timetableQueries.getTimetableSlots,new Object[]{user_id},(resultSet, i) -> {
            Slot slot = null;
            try {
                slot = slotDao.findById(resultSet.getInt("SLOT_ID"), user_id);
            } catch (BadRequestException e) {
                e.printStackTrace();
            }
            var subject = subjectRepository.findById(resultSet.getInt("SUBJECT_ID"));

            return SubjectSlots.builder()
                    .id(resultSet.getInt("ID"))
                    .subject(subject.get())
                    .slot(slot)
                    .week(resultSet.getInt("WEEK"))
                    .build();
        });
    }

    @Transactional
    public void clear(Integer user_id) {
        var clearSubjectSlots = jdbcTemplate.update(timetableQueries.clearSubjectSlots, user_id);
        var clearSubjects = jdbcTemplate.update(timetableQueries.clearSubjects, user_id);
        var clearSlots = jdbcTemplate.update(timetableQueries.clearSlots, user_id);
    }
}
