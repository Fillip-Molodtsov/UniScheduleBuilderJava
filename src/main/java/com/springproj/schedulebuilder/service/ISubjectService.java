package com.springproj.schedulebuilder.service;

import com.springproj.schedulebuilder.exception.NoSuchSubjectException;
import com.springproj.schedulebuilder.model.domain.subject.Subject;
import com.springproj.schedulebuilder.model.dto.subject.SubjectCreationDto;

import java.util.List;

public interface ISubjectService {

    void create(SubjectCreationDto subjectDto);

    void update(Subject subjectDto) throws NoSuchSubjectException;

    Subject getById(Integer subjectId) throws NoSuchSubjectException;

    void delete(Integer subjectId);

    List<Subject> getAll();
}
