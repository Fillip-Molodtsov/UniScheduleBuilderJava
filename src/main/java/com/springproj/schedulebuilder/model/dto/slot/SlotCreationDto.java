package com.springproj.schedulebuilder.model.dto.slot;

import lombok.Data;

import javax.security.auth.Subject;

@Data
public class SlotCreationDto {
    public String day;
    public String time;
    public Subject subject;
    public Boolean lection;
    public String room;
    public Integer week;
}
