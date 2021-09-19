package com.springproj.schedulebuilder.controller;

import com.springproj.schedulebuilder.exception.NoSuchDayException;
import com.springproj.schedulebuilder.exception.NoSuchIntervalException;
import com.springproj.schedulebuilder.exception.NoSuchSlotException;
import com.springproj.schedulebuilder.exception.NoSuchSubjectException;
import com.springproj.schedulebuilder.model.domain.slot.Slot;
import com.springproj.schedulebuilder.model.dto.slot.SlotCreationBodyDto;
import com.springproj.schedulebuilder.model.dto.slot.SlotCreationDto;
import com.springproj.schedulebuilder.model.dto.slot.SlotUpdateDto;
import com.springproj.schedulebuilder.service.IDayService;
import com.springproj.schedulebuilder.service.IIntervalsService;
import com.springproj.schedulebuilder.service.ISlotService;
import com.springproj.schedulebuilder.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class SlotController {
    private ISubjectService iSubjectService;
    private ISlotService iSlotService;
    private IDayService iDayService;
    private IIntervalsService iIntervalsService;

    @Autowired
    public SlotController(
            ISubjectService iSubjectService,
            ISlotService iSlotService,
            IIntervalsService iIntervalsService,
            IDayService iDayService
    ) {
        this.iSubjectService = iSubjectService;
        this.iSlotService = iSlotService;
        this.iDayService = iDayService;
        this.iIntervalsService = iIntervalsService;
    }


    @PostMapping("sub/{subjectId}/slot")
    void create(
            @RequestBody SlotCreationBodyDto slotCreationBodyDto,
            @PathVariable Integer subjectId
    ) throws NoSuchSubjectException, NoSuchDayException, NoSuchIntervalException {
        var subject = iSubjectService.getById(subjectId);

        slotCreationBodyDto.weeks.forEach(week -> {
            var slotCreationDto = SlotCreationDto.builder()
                    .day(slotCreationBodyDto.day)
                    .lection(slotCreationBodyDto.lection)
                    .subject(subjectId)
                    .time(slotCreationBodyDto.time)
                    .week(week)
                    .room(slotCreationBodyDto.room)
                    .build();

            try {
                iSlotService.create(slotCreationDto);
            } catch (NoSuchDayException | NoSuchIntervalException | NoSuchSubjectException e) {
                e.printStackTrace();
            }
        });
    }

    @PutMapping("sub/{subjectId}/slot")
    void update(
            @RequestBody SlotUpdateDto slotUpdateDto,
            @PathVariable Integer subjectId
    ) throws NoSuchSubjectException, NoSuchSlotException, NoSuchDayException, NoSuchIntervalException {
        var subject = iSubjectService.getById(subjectId);
        var day = iDayService.getById(slotUpdateDto.day);
        var interval = iIntervalsService.getById(slotUpdateDto.time);

        var slot = Slot.builder()
                .id(slotUpdateDto.id)
                .day(day)
                .lection(slotUpdateDto.lection)
                .room(slotUpdateDto.room)
                .week(slotUpdateDto.week)
                .subject(subject)
                .time(interval)
                .build();


        iSlotService.update(slot);
    }

    @GetMapping("sub/{subjectId}/slot/{slotId}")
    Slot getSlot(
            @PathVariable Integer slotId,
            @PathVariable String subjectId
    ) throws NoSuchSlotException {
        return iSlotService.getById(slotId);
    }

    @DeleteMapping("slot/{slotId}")
    void delete(@PathVariable Integer slotId) {
        iSlotService.delete(slotId);
    }
}
