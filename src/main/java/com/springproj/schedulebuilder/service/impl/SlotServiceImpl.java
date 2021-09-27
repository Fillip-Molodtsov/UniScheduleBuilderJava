package com.springproj.schedulebuilder.service.impl;

import com.springproj.schedulebuilder.exception.NoSuchDayException;
import com.springproj.schedulebuilder.exception.NoSuchIntervalException;
import com.springproj.schedulebuilder.exception.NoSuchSlotException;
import com.springproj.schedulebuilder.exception.NoSuchSubjectException;
import com.springproj.schedulebuilder.model.domain.slot.Slot;
import com.springproj.schedulebuilder.model.dto.slot.SlotCreationDto;
import com.springproj.schedulebuilder.repository.DaysRepository;
import com.springproj.schedulebuilder.repository.IntervalsRepository;
import com.springproj.schedulebuilder.repository.SlotRepository;
import com.springproj.schedulebuilder.repository.SubjectRepository;
import com.springproj.schedulebuilder.repository.daos.SlotDaoImpl;
import com.springproj.schedulebuilder.repository.queries.SlotQueries;
import com.springproj.schedulebuilder.service.ISlotService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlotServiceImpl implements ISlotService {
    private final SlotRepository slotRepository;
    private final DaysRepository daysRepository;
    private final IntervalsRepository intervalsRepository;
    private final SubjectRepository subjectRepository;
    private final SlotDaoImpl slotDao;

    SlotServiceImpl(
            SlotRepository slotRepository,
            DaysRepository daysRepository,
            IntervalsRepository intervalsRepository,
            SubjectRepository subjectRepository,
            SlotDaoImpl slotDao
    ) {
        this.slotRepository = slotRepository;
        this.daysRepository = daysRepository;
        this.intervalsRepository = intervalsRepository;
        this.subjectRepository = subjectRepository;
        this.slotDao = slotDao;
    }

    @Override
    public void create(SlotCreationDto slotCreationDto) throws NoSuchDayException, NoSuchIntervalException, NoSuchSubjectException {
        var day = daysRepository.findById(slotCreationDto.day).orElseThrow(NoSuchDayException::new);
        var interval = intervalsRepository.findById(slotCreationDto.time).orElseThrow(NoSuchIntervalException::new);
        var subject = subjectRepository.findById(slotCreationDto.subject).orElseThrow(NoSuchSubjectException::new);

        slotDao.save(slotCreationDto);
    }

    @Override
    public void update(Slot slotDto) throws NoSuchSlotException {
        var slotToUpdate = slotRepository.findById(slotDto.getId()).orElseThrow(NoSuchSlotException::new);

        slotToUpdate.setDay(slotDto.getDay());
        slotToUpdate.setLection(slotDto.getLection());
        slotToUpdate.setRoom(slotDto.getRoom());
        slotToUpdate.setTime(slotDto.getTime());

        slotRepository.save(slotToUpdate);

    }

    @Override
    public Slot getById(Integer slotId) throws NoSuchSlotException {
        return slotRepository.findById(slotId).orElseThrow(NoSuchSlotException::new);
    }

    @Override
    public void delete(Integer slotId) {
        slotDao.delete(slotId);
    }

    @Override
    public List<Slot> getAll() {
        return (List<Slot>) slotRepository.findAll();
    }

}
