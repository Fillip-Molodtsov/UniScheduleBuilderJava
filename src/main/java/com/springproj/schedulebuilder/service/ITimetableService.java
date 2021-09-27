package com.springproj.schedulebuilder.service;

import com.springproj.schedulebuilder.model.dto.timetable.Timetable;

import java.util.List;

public interface ITimetableService {
    List<Timetable> getTimetable(Integer query);
}
