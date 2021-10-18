package com.springproj.schedulebuilder.controller;

import com.springproj.schedulebuilder.exception.*;
import com.springproj.schedulebuilder.model.domain.slot.Slot;
import com.springproj.schedulebuilder.model.dto.slot.SlotCreationBodyDto;
import com.springproj.schedulebuilder.model.dto.slot.SlotCreationDto;
import com.springproj.schedulebuilder.model.dto.slot.SlotUpdateDto;
import com.springproj.schedulebuilder.service.IDayService;
import com.springproj.schedulebuilder.service.IIntervalsService;
import com.springproj.schedulebuilder.service.ISlotService;
import com.springproj.schedulebuilder.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
            @RequestBody @Valid SlotCreationBodyDto slotCreationBodyDto,
            @PathVariable Integer subjectId,
            Authentication authentication
    ) throws NoSuchSubjectException, NoSuchDayException, NoSuchIntervalException {
        var username = authentication.getName();
        var slotCreationDto = SlotCreationDto.builder()
                .day(slotCreationBodyDto.day)
                .lection(slotCreationBodyDto.lection)
                .subject(subjectId)
                .time(slotCreationBodyDto.time)
                .room(slotCreationBodyDto.room)
                .weeks(slotCreationBodyDto.weeks)
                .build();

        try {
            iSlotService.create(slotCreationDto, username);
        } catch (NoSuchDayException | NoSuchIntervalException | NoSuchSubjectException | BadRequestException e) {
            e.printStackTrace();
        }
    }

    @PutMapping("sub/{subjectId}/slot")
    void update(
            @RequestBody SlotUpdateDto slotUpdateDto,
            @PathVariable Integer subjectId,
            Authentication authentication
    ) throws NoSuchSubjectException, NoSuchSlotException, NoSuchDayException, NoSuchIntervalException, BadRequestException, NoSuchFieldException {
        var username = authentication.getName();
        var subject = iSubjectService.getById(subjectId, username);
        var day = iDayService.getById(slotUpdateDto.day);
        var interval = iIntervalsService.getById(slotUpdateDto.time);

        var slot = Slot.builder()
                .id(slotUpdateDto.id)
                .day(day)
                .lection(slotUpdateDto.lection)
                .room(slotUpdateDto.room)
                .time(interval)
                .build();


        iSlotService.update(slot, username);
    }

    @GetMapping("sub/{subjectId}/slot/{slotId}")
    Slot getSlot(
            @PathVariable Integer slotId,
            @PathVariable String subjectId,
            Authentication authentication
    ) throws NoSuchSlotException, BadRequestException {
        var username = authentication.getName();
        return iSlotService.getById(slotId, username);
    }

    @DeleteMapping("slot/{slotId}")
    void delete(
            @PathVariable Integer slotId,
            Authentication authentication
    ) throws NoSuchSlotException, BadRequestException {
        var username = authentication.getName();
        iSlotService.delete(slotId, username);
    }

    @PatchMapping("slot/{slotId}/{username}")
    void patchSlot(
            @PathVariable Integer slotId,
            @PathVariable String username,
            Authentication authentication
    ) throws NoSuchSlotException, BadRequestException {
        var ownerUsername = authentication.getName();
        iSlotService.patch(slotId, ownerUsername, username);
    }
}
