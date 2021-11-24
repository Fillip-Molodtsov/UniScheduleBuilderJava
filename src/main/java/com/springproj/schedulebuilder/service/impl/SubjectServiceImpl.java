package com.springproj.schedulebuilder.service.impl;

import com.springproj.schedulebuilder.config.LogMarkers;
import com.springproj.schedulebuilder.exception.BadRequestException;
import com.springproj.schedulebuilder.exception.NoSuchSubjectException;
import com.springproj.schedulebuilder.model.domain.subject.Subject;
import com.springproj.schedulebuilder.model.dto.subject.FullSubject;
import com.springproj.schedulebuilder.model.dto.subject.SubjectCreationDto;
import com.springproj.schedulebuilder.repository.AppUserRepository;
import com.springproj.schedulebuilder.repository.SlotRepository;
import com.springproj.schedulebuilder.repository.SubjectRepository;
import com.springproj.schedulebuilder.repository.daos.SlotDaoImpl;
import com.springproj.schedulebuilder.service.ISubjectService;
import com.springproj.schedulebuilder.util.LogExecTime;
import com.springproj.schedulebuilder.util.LogSignature;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubjectServiceImpl implements ISubjectService {
    private SubjectRepository subjectRepository;
    private SlotRepository slotRepository;
    private SlotDaoImpl slotDao;
    private AppUserRepository appUserRepository;
    private static final Logger logger = LoggerFactory.getLogger(SubjectServiceImpl.class);

    @Override
    public void create(@Valid SubjectCreationDto subjectDto, String username) throws BadRequestException {
        var user = appUserRepository.findByUsername(username).orElseThrow(BadRequestException::new);
        subjectRepository.save(Subject.builder()
                .name(subjectDto.name)
                .lecturer(subjectDto.lecturer)
                .practitioner(subjectDto.practitioner)
                .user_id(user.getId())
                .build());
        MDC.put("user.id", user.getId().toString());
        var confidentialMarker = MarkerFactory.getMarker(LogMarkers.CONFIDENTIAL.name());
        MDC.put("user.id", user.getId().toString());
        logger.trace(confidentialMarker, "User {} created a subject", user.getUsername());
        logger.trace("Subject {} created", subjectDto.name);
        MDC.clear();
    }

    @Override
    public void update(Subject newSubject, String username) throws NoSuchSubjectException, BadRequestException {
        var user = appUserRepository.findByUsername(username).orElseThrow(BadRequestException::new);
        var subjectToUpdate = subjectRepository.findById(newSubject.getId()).orElseThrow(NoSuchSubjectException::new);
        if (!Objects.equals(subjectToUpdate.getUser_id(), user.getId())) {
            throw new BadRequestException("This user doesn't own this subject");
        }
        subjectToUpdate.setName(newSubject.getName());
        subjectToUpdate.setLecturer(newSubject.getLecturer());
        subjectToUpdate.setPractitioner(newSubject.getPractitioner());
        subjectToUpdate.setUser_id(user.getId());
        subjectRepository.save(subjectToUpdate);
    }

    @Override
    @LogSignature
    public Subject getById(Integer subjectId, String username) throws NoSuchSubjectException, BadRequestException {
        var user = appUserRepository.findByUsername(username).orElseThrow(BadRequestException::new);
        var subject = subjectRepository.findById(subjectId).orElseThrow(NoSuchSubjectException::new);
        if (!Objects.equals(user.getId(), subject.getUser_id())) {
            throw new BadRequestException("This user doesn't own this subject");
        }
        return subject;
    }

    @Override
    @LogExecTime
    public List<Subject> getAll(String username) throws BadRequestException {
        var user = appUserRepository.findByUsername(username).orElseThrow(BadRequestException::new);
        var subjects = (List<Subject>) subjectRepository.findAll();
        return subjects.stream().filter(el -> Objects.equals(el.getUser_id(), user.getId())).collect(Collectors.toList());
    }

    @Override
    public FullSubject getFullById(Integer subjectId, String username) throws NoSuchSubjectException, BadRequestException {
        var subject = subjectRepository.findById(subjectId).orElseThrow(NoSuchSubjectException::new);
        var user = appUserRepository.findByUsername(username).orElseThrow(BadRequestException::new);
        if (!Objects.equals(user.getId(), subject.getUser_id())) {
            throw new BadRequestException("This user doesn't own this subject");
        }
        var slots = slotDao.findByUserSubject(subject.getId(), user.getId());
        return FullSubject.builder()
                .name(subject.getName())
                .lecturer(subject.getLecturer())
                .practitioner(subject.getPractitioner())
                .subjectId(subject.getId())
                .slots(slots)
                .userId(user.getId())
                .build();
    }

    @Override
    public void delete(Integer subjectId, String username) throws BadRequestException, NoSuchSubjectException {
        var user = appUserRepository.findByUsername(username).orElseThrow(BadRequestException::new);
        var subject = subjectRepository.findById(subjectId).orElseThrow(NoSuchSubjectException::new);
        if (!Objects.equals(user.getId(), subject.getUser_id())) {
            throw new BadRequestException("This user doesn't own this subject");
        }
        subjectRepository.deleteById(subjectId);
    }
}
