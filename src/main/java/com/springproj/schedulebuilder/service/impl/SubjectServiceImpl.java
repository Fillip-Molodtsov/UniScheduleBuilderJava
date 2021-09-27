package com.springproj.schedulebuilder.service.impl;

import com.springproj.schedulebuilder.exception.NoSuchSubjectException;
import com.springproj.schedulebuilder.model.domain.slot.Slot;
import com.springproj.schedulebuilder.model.domain.subject.Subject;
import com.springproj.schedulebuilder.model.dto.subject.FullSubject;
import com.springproj.schedulebuilder.model.dto.subject.SubjectCreationDto;
import com.springproj.schedulebuilder.repository.SlotRepository;
import com.springproj.schedulebuilder.repository.SubjectRepository;
import com.springproj.schedulebuilder.service.ISubjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements ISubjectService {
    private SubjectRepository subjectRepository;
    private SlotRepository slotRepository;

    SubjectServiceImpl(
            SubjectRepository subjectRepository,
            SlotRepository slotRepository
    ) {
        this.subjectRepository = subjectRepository;
        this.slotRepository = slotRepository;
    }

    @Override
    public void create(SubjectCreationDto subjectDto) {
        var subject = Subject.builder()
                .name(subjectDto.name)
                .lecturer(subjectDto.lecturer)
                .practitioner(subjectDto.practitioner)
                .build();
        subjectRepository.save(subject);
    }

    @Override
    public void update(Subject newSubject) throws NoSuchSubjectException {
        var subjectToUpdate = subjectRepository.findById(newSubject.getId()).orElseThrow(NoSuchSubjectException::new);
        subjectToUpdate.setName(newSubject.getName());
        subjectToUpdate.setLecturer(newSubject.getLecturer());
        subjectToUpdate.setPractitioner(newSubject.getPractitioner());

        subjectRepository.save(subjectToUpdate);
    }

    @Override
    public Subject getById(Integer subjectId) throws NoSuchSubjectException {
       return subjectRepository.findById(subjectId).orElseThrow(NoSuchSubjectException::new);
    }
    @Override
    public List<Subject> getAll(){
        return (List<Subject>) subjectRepository.findAll();
    }

    @Override
    public FullSubject getFullById(Integer subjectId) throws NoSuchSubjectException {
        var subject = subjectRepository.findById(subjectId).orElseThrow(NoSuchSubjectException::new);
        var slots = (List<Slot>) slotRepository.findAll();
        return FullSubject.builder()
                .name(subject.getName())
                .lecturer(subject.getLecturer())
                .practitioner(subject.getPractitioner())
                .subjectId(subject.getId())
                .slots(slots)
                .build();
    }

    @Override
    public void delete(Integer subjectId) {
        subjectRepository.deleteById(subjectId);
    }
}
