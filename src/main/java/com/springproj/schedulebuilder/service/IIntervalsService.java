package com.springproj.schedulebuilder.service;

import com.springproj.schedulebuilder.exception.NoSuchIntervalException;
import com.springproj.schedulebuilder.model.domain.intervals.Interval;
import com.springproj.schedulebuilder.model.dto.intervals.IntervalsCreationDto;

import java.util.List;

public interface IIntervalsService {
    void create(IntervalsCreationDto intervalsCreationDto);

    void update(Interval intervalDto) throws NoSuchIntervalException;

    Interval getById(Integer intervalId) throws NoSuchIntervalException;

    void delete(Integer intervalId);

    List<Interval> getAll();
}
