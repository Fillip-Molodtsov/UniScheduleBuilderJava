package com.springproj.schedulebuilder.controller;

import com.springproj.schedulebuilder.exception.BadRequestException;
import com.springproj.schedulebuilder.model.dto.timetable.Timetable;
import com.springproj.schedulebuilder.service.ITimetableService;
import com.springproj.schedulebuilder.service.IUserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam(required = false, name = "query") Integer query,
            Authentication authentication
    ) throws NoSuchFieldException {
        final var name = authentication.getName();
        return iTimetableService.getTimetable(query, name);
    }

    @PostMapping("clear")
    void clear(
        Authentication authentication
    ) throws BadRequestException {
        var username = authentication.getName();
        iTimetableService.clear(username);
    }
}
