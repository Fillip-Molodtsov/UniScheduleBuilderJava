package com.springproj.schedulebuilder;

import com.springproj.schedulebuilder.model.domain.days.Day;
import com.springproj.schedulebuilder.model.domain.intervals.Interval;
import com.springproj.schedulebuilder.model.domain.slot.Slot;
import com.springproj.schedulebuilder.model.domain.subject.Subject;
import com.springproj.schedulebuilder.repository.DaysRepository;
import com.springproj.schedulebuilder.repository.IntervalsRepository;
import com.springproj.schedulebuilder.repository.SlotRepository;
import com.springproj.schedulebuilder.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
public class DataJPATest {
    @Autowired
    DaysRepository daysRepository;
    @Autowired
    IntervalsRepository intervalsRepository;
    @Autowired
    SubjectRepository subjectRepository;

    @Test
    public void daysTest() {
        Iterable<Day> days = daysRepository.findAll();
        assertThat(days).hasSize(7);
    }

    @Test
    public void intervalsTest() {
        Iterable<Interval> intervals = intervalsRepository.findAll();
        assertThat(intervals).hasSize(7);
    }

    @Test
    public void subjectTest() {
        Iterable<Subject> subjects = subjectRepository.findAll();
        subjects.forEach(subject -> {
            assertThat(subject.getLecturer()).isNotBlank().isNotNull();
            assertThat(subject.getPractitioner()).isNotBlank().isNotNull();
            assertThat(subject.getName()).isNotBlank().isNotNull();
        });
    }
}
