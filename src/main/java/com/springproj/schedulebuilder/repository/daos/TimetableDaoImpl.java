package com.springproj.schedulebuilder.repository.daos;

import com.springproj.schedulebuilder.model.domain.slot.Slot;
import com.springproj.schedulebuilder.model.domain.slot.SubjectSlots;
import com.springproj.schedulebuilder.model.dto.timetable.Timetable;
import com.springproj.schedulebuilder.repository.SlotRepository;
import com.springproj.schedulebuilder.repository.SubjectRepository;
import com.springproj.schedulebuilder.repository.queries.TimetableQueries;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TimetableDaoImpl {
    private final JdbcTemplate jdbcTemplate;
    private final TimetableQueries timetableQueries;
    private final SlotRepository slotRepository;
    private final SubjectRepository subjectRepository;

    TimetableDaoImpl(
            JdbcTemplate jdbcTemplate,
            SlotRepository slotRepository,
            SubjectRepository subjectRepository
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.slotRepository = slotRepository;
        this.subjectRepository = subjectRepository;
        this.timetableQueries = new TimetableQueries();
    }

    public List<SubjectSlots> getSubjectSlots() {
        return jdbcTemplate.query(timetableQueries.getTimetableSlots, (resultSet, i) -> {
            var slot = slotRepository.findById(resultSet.getInt("SLOT_ID"));
            var subject = subjectRepository.findById(resultSet.getInt("SUBJECT_ID"));

            return SubjectSlots.builder()
                    .id(resultSet.getInt("ID"))
                    .subject(subject.get())
                    .slot(slot.get())
                    .week(resultSet.getInt("WEEK"))
                    .build();
        });
    }
}
