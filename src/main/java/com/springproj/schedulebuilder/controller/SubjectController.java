package com.springproj.schedulebuilder.controller;

import com.springproj.schedulebuilder.exception.BadRequestException;
import com.springproj.schedulebuilder.exception.NoSuchSubjectException;
import com.springproj.schedulebuilder.model.domain.subject.Subject;
import com.springproj.schedulebuilder.model.dto.subject.FullSubject;
import com.springproj.schedulebuilder.model.dto.subject.SubjectCreationDto;
import com.springproj.schedulebuilder.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sub")
public class SubjectController {
    private ISubjectService iSubjectService;

    @Autowired
    public void SubjectController(ISubjectService iSubjectService) {
        this.iSubjectService = iSubjectService;
    }

    @PostMapping()
    void create(
            @RequestBody SubjectCreationDto subjectDto,
            Authentication authentication
    ) throws BadRequestException {
        var username = authentication.getName();
        iSubjectService.create(subjectDto, username);
    }

    @PutMapping()
    void update(
            @RequestBody Subject subject,
            Authentication authentication
    ) throws NoSuchSubjectException, BadRequestException {
        var username = authentication.getName();
        iSubjectService.update(subject, username);
    }

    @GetMapping("/all")
    List<Subject> getAll(
            Authentication authentication
    ) throws NoSuchSubjectException, BadRequestException {
        var username = authentication.getName();
        return iSubjectService.getAll(username);
    }

    @GetMapping("/{id}")
    Subject getById(
            @PathVariable Integer id,
            Authentication authentication
    ) throws NoSuchSubjectException, BadRequestException {
        var username = authentication.getName();
       return iSubjectService.getById(id, username);
    }

    @DeleteMapping("/{id}")
    void delete(
            @PathVariable Integer id,
            Authentication authentication
    ) throws BadRequestException, NoSuchSubjectException {
        var username = authentication.getName();
        iSubjectService.delete(id, username);
    }

    @GetMapping("/{id}/full")
    FullSubject getFullById(
            @PathVariable Integer id,
            Authentication authentication
    ) throws NoSuchSubjectException, BadRequestException {
        var username = authentication.getName();
        return iSubjectService.getFullById(id, username);
    }
}
