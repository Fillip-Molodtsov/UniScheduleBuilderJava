package com.springproj.schedulebuilder.service.impl;

import com.springproj.schedulebuilder.exception.NoSuchDayException;
import com.springproj.schedulebuilder.model.domain.days.Day;
import com.springproj.schedulebuilder.model.dto.days.DayCreationDto;
import com.springproj.schedulebuilder.repository.DaysRepository;
import com.springproj.schedulebuilder.service.IDayService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DayServiceImpl implements IDayService {
    private final DaysRepository daysRepository;

    DayServiceImpl(DaysRepository daysRepository) {
        this.daysRepository = daysRepository;
    }

    @Override
    public void create(DayCreationDto dayCreationDto) {
        var day = Day.builder()
                .value(dayCreationDto.value)
                .build();

        daysRepository.save(day);
    }

    @Override
    public void update(Day dayDto) throws NoSuchDayException {
        var day = daysRepository.findById(dayDto.getId()).orElseThrow(NoSuchDayException::new);

        day.setValue(dayDto.getValue());

        daysRepository.save(day);
    }

    @Override
    public Day getById(Integer dayId) throws NoSuchDayException {
        return daysRepository.findById(dayId).orElseThrow(NoSuchDayException::new);
    }

    @Override
    public void delete(Integer dayId) {
        daysRepository.deleteById(dayId);
    }

    @Override
    @Cacheable(value = "days")
    public List<Day> getAll() {
        return (List<Day>) daysRepository.findAll();
    }
}
