package com.springproj.schedulebuilder.service.impl;

import com.springproj.schedulebuilder.exception.BadRequestException;
import com.springproj.schedulebuilder.model.domain.days.Day;
import com.springproj.schedulebuilder.model.domain.slot.SubjectSlots;
import com.springproj.schedulebuilder.model.dto.slot.SlotFormatted;
import com.springproj.schedulebuilder.model.dto.timetable.Timetable;
import com.springproj.schedulebuilder.model.dto.timetable.TimetableSlot;
import com.springproj.schedulebuilder.repository.AppUserRepository;
import com.springproj.schedulebuilder.repository.DaysRepository;
import com.springproj.schedulebuilder.repository.SubjectSlotsRepository;
import com.springproj.schedulebuilder.repository.daos.TimetableDaoImpl;
import com.springproj.schedulebuilder.service.ITimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TimetableServiceImpl implements ITimetableService {
    private final DaysRepository daysRepository;
    private final TimetableDaoImpl timetableDao;
    private final AppUserRepository appUserRepository;

    @Autowired
    TimetableServiceImpl(
            DaysRepository daysRepository,
            TimetableDaoImpl timetableDao,
            AppUserRepository appUserRepository
    ) {
        this.daysRepository = daysRepository;
        this.timetableDao = timetableDao;
        this.appUserRepository = appUserRepository;
    }

    public List<Timetable> getTimetable(Integer query, String username) throws NoSuchFieldException {
        List<Day> days = (List<Day>) daysRepository.findAll();
        var user = appUserRepository.findByUsername(username).orElseThrow(NoSuchFieldException::new);
        List<SubjectSlots> subjectSlots = timetableDao.getSubjectSlots(user.getId());
        List<SlotFormatted> slots = subjectSlots.stream().map(subjectSlot -> {
            String teacher = subjectSlot.getSlot().getLection() ?
                    subjectSlot.getSubject().getLecturer() :
                    subjectSlot.getSubject().getPractitioner();

            return SlotFormatted.builder()
                    .subject(subjectSlot.getSubject().getName())
                    .group(subjectSlot.getSlot().getLection().toString())
                    .teacher(teacher)
                    .week(subjectSlot.getWeek())
                    .room(subjectSlot.getSlot().getRoom())
                    .time(subjectSlot.getSlot().getTime().getValue())
                    .day(subjectSlot.getSlot().getDay().getValue())
                    .build();
        }).collect(Collectors.toList());
        List<SlotFormatted> finalSlots;
        if (query != null)
            finalSlots = slots.stream().filter(slotFormatted -> Objects.equals(slotFormatted.getWeek(), query)).collect(Collectors.toList());
        else
            finalSlots = slots;
        return days.stream().map(day -> {
            List<TimetableSlot> timetableSlots = finalSlots.stream().filter(slotFormatted -> Objects.equals(slotFormatted.getDay(), day.getValue()))
                    .map(slotFormatted -> TimetableSlot.builder()
                            .time(slotFormatted.getTime())
                            .room(slotFormatted.getRoom())
                            .group(slotFormatted.getGroup())
                            .teacher(slotFormatted.getTeacher())
                            .subject(slotFormatted.getSubject())
                            .week(slotFormatted.getWeek())
                            .build())
                    .collect(Collectors.toList());
            return Timetable.builder()
                    .day(day.getValue())
                    .slots(timetableSlots)
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public void clear(String username) throws BadRequestException {
        var user = appUserRepository.findByUsername(username).orElseThrow(BadRequestException::new);
        timetableDao.clear(user.getId());
    }
}
