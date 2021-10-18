package com.springproj.schedulebuilder.model.dto.slot;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SlotCreationBodyDto {
    @NotNull
    @Min(1)
    @Max(7)
    public Integer day;

    @NotNull
    @Min(1)
    @Max(7)
    public Integer time;

    public Boolean lection;

    @NotEmpty()
    public String room;

    public List<Integer> weeks;
}
