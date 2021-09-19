package com.springproj.schedulebuilder.controller;

import com.springproj.schedulebuilder.exception.NoSuchDayException;
import com.springproj.schedulebuilder.model.domain.days.Day;
import com.springproj.schedulebuilder.model.dto.days.DayCreationDto;
import com.springproj.schedulebuilder.service.IDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sub/days")
public class DaysController {
    private IDayService iDayService;

    @Autowired
    public DaysController(IDayService iDayService) {
        this.iDayService = iDayService;
    }

    @GetMapping()
    List<Day> getAll() {
        return iDayService.getAll();
    }
}
