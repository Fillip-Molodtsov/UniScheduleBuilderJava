package com.springproj.schedulebuilder.service;

import com.springproj.schedulebuilder.exception.BadRequestException;
import com.springproj.schedulebuilder.exception.NoSuchSubjectException;
import com.springproj.schedulebuilder.model.domain.subject.Subject;
import com.springproj.schedulebuilder.model.dto.subject.FullSubject;
import com.springproj.schedulebuilder.model.dto.subject.SubjectCreationDto;

import java.util.List;

public interface ISubjectService {

    void create(SubjectCreationDto subjectDto, String username) throws BadRequestException;

    void update(Subject subjectDto, String username) throws NoSuchSubjectException, BadRequestException;

    Subject getById(Integer subjectId, String username) throws NoSuchSubjectException, BadRequestException;

    void delete(Integer subjectId, String username) throws BadRequestException, NoSuchSubjectException;

    List<Subject> getAll(String username) throws BadRequestException;

    FullSubject getFullById(Integer subjectId, String username) throws NoSuchSubjectException, BadRequestException;
}
