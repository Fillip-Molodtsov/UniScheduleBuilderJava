package com.springproj.schedulebuilder.service;

import com.springproj.schedulebuilder.exception.NoSuchSlotException;
import com.springproj.schedulebuilder.model.domain.slot.Slot;
import com.springproj.schedulebuilder.model.dto.slot.SlotCreationDto;

import java.util.List;

public interface ISlotService {
    void create(SlotCreationDto slotCreationDto);

    void update(Slot slotDto) throws NoSuchSlotException;

    Slot getById(Integer slotId) throws NoSuchSlotException;

    void delete(Integer slotId);

    List<Slot> getAll();
}
