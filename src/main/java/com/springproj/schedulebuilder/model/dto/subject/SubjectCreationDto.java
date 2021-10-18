package com.springproj.schedulebuilder.model.dto.subject;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class SubjectCreationDto {
    @NotEmpty()
    public String name;
    @NotEmpty()
    public String lecturer;
    @NotEmpty()
    public String practitioner;

    private Integer user_id;
}
