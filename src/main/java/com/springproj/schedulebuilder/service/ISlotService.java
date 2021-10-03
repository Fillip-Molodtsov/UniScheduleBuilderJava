package com.springproj.schedulebuilder.service;

import com.springproj.schedulebuilder.exception.*;
import com.springproj.schedulebuilder.model.domain.slot.Slot;
import com.springproj.schedulebuilder.model.dto.slot.SlotCreationDto;

import java.util.List;

public interface ISlotService {
    void create(SlotCreationDto slotCreationDto, String username) throws NoSuchDayException, NoSuchIntervalException, NoSuchSubjectException, BadRequestException;

    void update(Slot slotDto, String username) throws NoSuchSlotException, NoSuchFieldException, BadRequestException;

    Slot getById(Integer slotId, String username) throws NoSuchSlotException, BadRequestException;

    void delete(Integer slotId, String username) throws NoSuchSlotException, BadRequestException;

    List<Slot> getAll();
}
