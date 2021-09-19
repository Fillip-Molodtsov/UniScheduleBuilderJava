package com.springproj.schedulebuilder.controller;

import com.springproj.schedulebuilder.exception.NoSuchIntervalException;
import com.springproj.schedulebuilder.model.domain.intervals.Interval;
import com.springproj.schedulebuilder.model.dto.intervals.IntervalsCreationDto;
import com.springproj.schedulebuilder.service.IIntervalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sub/time-intervals")
public class IntervalsController {
    private IIntervalsService iIntervalsService;

    @Autowired
    public void IntervalsController(IIntervalsService iIntervalsService) {
        this.iIntervalsService = iIntervalsService;
    }

    @PostMapping()
    void create(@RequestBody IntervalsCreationDto intervalsCreationDto) {
        iIntervalsService.create(intervalsCreationDto);
    }

    @PutMapping()
    void update(@RequestBody Interval interval) throws NoSuchIntervalException {
        iIntervalsService.update(interval);
    }

    @GetMapping()
    List<Interval> getAll() throws NoSuchIntervalException {
        return iIntervalsService.getAll();
    }
}
