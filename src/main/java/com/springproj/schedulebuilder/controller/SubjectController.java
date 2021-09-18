package com.springproj.schedulebuilder.controller;

import com.springproj.schedulebuilder.exception.NoSuchSubjectException;
import com.springproj.schedulebuilder.model.domain.subject.Subject;
import com.springproj.schedulebuilder.model.dto.subject.SubjectCreationDto;
import com.springproj.schedulebuilder.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
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
    void create(@RequestBody SubjectCreationDto subjectDto) {
        iSubjectService.create(subjectDto);
    }

    @PutMapping()
    void update(@RequestBody Subject subject) throws NoSuchSubjectException {
        iSubjectService.update(subject);
    }

    @GetMapping("/all")
    List<Subject> getAll() throws NoSuchSubjectException {
        return iSubjectService.getAll();
    }

    @GetMapping("/{id}")
    Subject getById(@PathVariable Integer id) throws NoSuchSubjectException {
       return iSubjectService.getById(id);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        iSubjectService.delete(id);
    }
}
