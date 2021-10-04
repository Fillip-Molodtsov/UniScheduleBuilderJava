package com.springproj.schedulebuilder.service;

import com.springproj.schedulebuilder.exception.BadRequestException;
import com.springproj.schedulebuilder.model.dto.timetable.Timetable;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface ITimetableService {
    List<Timetable> getTimetable(Integer query, String user) throws NoSuchFieldException;

    void clear(String username) throws BadRequestException;
}
