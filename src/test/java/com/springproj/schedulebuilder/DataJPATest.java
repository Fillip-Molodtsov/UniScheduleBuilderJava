package com.springproj.schedulebuilder;

import com.springproj.schedulebuilder.model.domain.days.Day;
import com.springproj.schedulebuilder.repository.DaysRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
public class DataJPATest {
    @Autowired
    DaysRepository repository;

    @Test
    public void daysTest() {
        Iterable<Day> days = repository.findAll();

        assertThat(days).hasSize(7);
    }
}
