package com.springproj.schedulebuilder.service.impl;

import com.springproj.schedulebuilder.exception.*;
import com.springproj.schedulebuilder.model.domain.slot.Slot;
import com.springproj.schedulebuilder.model.dto.slot.SlotCreationDto;
import com.springproj.schedulebuilder.repository.*;
import com.springproj.schedulebuilder.service.ISlotService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class SlotServiceImpl implements ISlotService {
    private final SlotRepository slotRepository;
    private final DaysRepository daysRepository;
    private final IntervalsRepository intervalsRepository;
    private final SubjectRepository subjectRepository;
    private final SlotDao slotDao;
    private final AppUserRepository appUserRepository;

    @Override
    public void create(SlotCreationDto slotCreationDto, String username) throws NoSuchDayException, NoSuchIntervalException, NoSuchSubjectException, BadRequestException {
        var day = daysRepository.findById(slotCreationDto.day).orElseThrow(NoSuchDayException::new);
        var interval = intervalsRepository.findById(slotCreationDto.time).orElseThrow(NoSuchIntervalException::new);
        var subject = subjectRepository.findById(slotCreationDto.subject).orElseThrow(NoSuchSubjectException::new);
        var user = appUserRepository.findByUsername(username).orElseThrow(BadRequestException::new);
        slotDao.save(slotCreationDto, user.getId());
    }

    @Override
    public void update(Slot slotDto, String username) throws NoSuchSlotException, BadRequestException {
        var slotToUpdate = slotRepository.findById(slotDto.getId()).orElseThrow(NoSuchSlotException::new);
        var user = appUserRepository.findByUsername(username).orElseThrow(BadRequestException::new);
        if (!Objects.equals(slotToUpdate.getUser_id(), user.getId()))
            throw new BadRequestException("This user doesn't own this slot");
        slotToUpdate.setDay(slotDto.getDay());
        slotToUpdate.setLection(slotDto.getLection());
        slotToUpdate.setRoom(slotDto.getRoom());
        slotToUpdate.setTime(slotDto.getTime());
        slotToUpdate.setUser_id(user.getId());
        slotRepository.save(slotToUpdate);

    }

    @Override
    public Slot getById(Integer slotId, String username) throws NoSuchSlotException, BadRequestException {
        var user = appUserRepository.findByUsername(username).orElseThrow(BadRequestException::new);
        //var slot = slotRepository.findById(slotId).orElseThrow(NoSuchSlotException::new);
        var slot = slotDao.findById(slotId, user.getId());
        if (!Objects.equals(slot.getUser_id(), user.getId()))
            throw new BadRequestException("This user doesn't own this slot");
        return slot;
    }

    @Override
    public void delete(Integer slotId, String username) throws NoSuchSlotException, BadRequestException {
        var user = appUserRepository.findByUsername(username).orElseThrow(BadRequestException::new);
        //var slot = slotRepository.findById(slotId).orElseThrow(NoSuchSlotException::new);
        var slot = slotDao.findById(slotId, user.getId());
        if (!Objects.equals(slot.getUser_id(), user.getId()))
            throw new BadRequestException("This user doesn't own this slot");
        slotDao.delete(slotId);
    }

    @Override
    public List<Slot> getAll() {
        return (List<Slot>) slotRepository.findAll();
    }

    @Override
    public void patch(Integer slotId, String ownerUsername, String username) throws NoSuchSlotException, BadRequestException {
        var slotToUpdate = slotRepository.findById(slotId).orElseThrow(NoSuchSlotException::new);
        var user = appUserRepository.findByUsername(ownerUsername).orElseThrow(BadRequestException::new);
        if (!Objects.equals(slotToUpdate.getUser_id(), user.getId()))
            throw new BadRequestException("This user doesn't own this slot");
        var newUser = appUserRepository.findByUsername(username).orElseThrow(BadRequestException::new);
        slotToUpdate.setUser_id(newUser.getId());
        slotRepository.save(slotToUpdate);
    }

}
