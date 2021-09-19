package com.springproj.schedulebuilder.service.impl;

import com.springproj.schedulebuilder.exception.NoSuchSlotException;
import com.springproj.schedulebuilder.model.domain.slot.Slot;
import com.springproj.schedulebuilder.model.dto.slot.SlotCreationDto;
import com.springproj.schedulebuilder.repository.SlotRepository;
import com.springproj.schedulebuilder.service.ISlotService;

import java.util.List;

public class SlotServiceImpl implements ISlotService {
    private final SlotRepository slotRepository;

    SlotServiceImpl(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    @Override
    public void create(SlotCreationDto slotCreationDto) {
        var slot = Slot.builder()
                .day(slotCreationDto.day)
                .lection(slotCreationDto.lection)
                .room(slotCreationDto.room)
                .time(slotCreationDto.time)
                .week(slotCreationDto.week)
                .subject(slotCreationDto.subject)
                .build();
        slotRepository.save(slot);

    }

    @Override
    public void update(Slot slotDto) throws NoSuchSlotException {
        var slotToUpdate = slotRepository.findById(slotDto.getId()).orElseThrow(NoSuchSlotException::new);
        slotToUpdate.setDay(slotDto.getDay());
        slotToUpdate.setLection(slotDto.getLection());
        slotToUpdate.setRoom(slotDto.getRoom());
        slotToUpdate.setSubject(slotDto.getSubject());
        slotToUpdate.setWeek(slotDto.getWeek());
        slotToUpdate.setTime(slotDto.getTime());

        slotRepository.save(slotToUpdate);

    }

    @Override
    public Slot getById(Integer slotId) throws NoSuchSlotException {
        return slotRepository.findById(slotId).orElseThrow(NoSuchSlotException::new);
    }

    @Override
    public void delete(Integer slotId) {
        slotRepository.deleteById(slotId);
    }

    @Override
    public List<Slot> getAll() {
        return (List<Slot>) slotRepository.findAll();
    }
}
