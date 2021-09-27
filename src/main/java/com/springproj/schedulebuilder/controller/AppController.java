package com.springproj.schedulebuilder.controller;

import com.springproj.schedulebuilder.model.dto.timetable.Timetable;
import com.springproj.schedulebuilder.service.ITimetableService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/app")
public class AppController {
    private ITimetableService iTimetableService;

    AppController(
            ITimetableService iTimetableService
    ) {
        this.iTimetableService = iTimetableService;
    }


    @GetMapping("timetable")
    List<Timetable> getTimetable(
            @RequestParam(required = false, name = "query") Integer query
    ) {
        return iTimetableService.getTimetable(query);
    }
}
