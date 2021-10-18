package com.springproj.schedulebuilder.model.dto.subject;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


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
