package com.springproj.schedulebuilder.model.dto.slot;

import lombok.Data;

import java.util.List;

@Data
public class SlotCreationBodyDto {
    public Integer day;
    public Integer time;
    public Boolean lection;
    public String room;
    public List<Integer> weeks;
}
