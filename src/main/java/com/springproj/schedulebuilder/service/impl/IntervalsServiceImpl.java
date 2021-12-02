package com.springproj.schedulebuilder.service.impl;

import com.springproj.schedulebuilder.exception.NoSuchIntervalException;
import com.springproj.schedulebuilder.model.domain.intervals.Interval;
import com.springproj.schedulebuilder.model.dto.intervals.IntervalsCreationDto;
import com.springproj.schedulebuilder.repository.IntervalsRepository;
import com.springproj.schedulebuilder.service.IIntervalsService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntervalsServiceImpl implements IIntervalsService {
    private final IntervalsRepository intervalsRepository;

    IntervalsServiceImpl(IntervalsRepository intervalsRepository) {
        this.intervalsRepository = intervalsRepository;
    }

    @Override
    public void create(IntervalsCreationDto intervalsCreationDto) {
        var interval = Interval.builder()
                .value(intervalsCreationDto.value)
                .build();

        intervalsRepository.save(interval);
    }

    @Override
    public void update(Interval intervalDto) throws NoSuchIntervalException {
        var interval = intervalsRepository.findById(intervalDto.getId()).orElseThrow(NoSuchIntervalException::new);

        interval.setValue(intervalDto.getValue());

        intervalsRepository.save(interval);
    }

    @Override
    public Interval getById(Integer intervalId) throws NoSuchIntervalException {
        return intervalsRepository.findById(intervalId).orElseThrow(NoSuchIntervalException::new);
    }

    @Override
    public void delete(Integer intervalId) {
        intervalsRepository.deleteById(intervalId);
    }

    @Override
    @Cacheable(value = "intervals")
    public List<Interval> getAll() {
        return (List<Interval>) intervalsRepository.findAll();
    }
}
