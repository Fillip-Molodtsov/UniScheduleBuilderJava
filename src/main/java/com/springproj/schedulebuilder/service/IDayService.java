package com.springproj.schedulebuilder.service;

import com.springproj.schedulebuilder.exception.NoSuchDayException;
import com.springproj.schedulebuilder.model.domain.days.Day;
import com.springproj.schedulebuilder.model.dto.days.DayCreationDto;

import java.util.List;

public interface IDayService {
    void create(DayCreationDto dayCreationDto);

    void update(Day dayDto) throws NoSuchDayException;

    Day getById(Integer dayId) throws NoSuchDayException;

    void delete(Integer dayId);

    List<Day> getAll();
}
