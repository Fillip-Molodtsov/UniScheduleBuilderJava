package com.springproj.schedulebuilder.model.dto.slot;

import lombok.Data;

import javax.security.auth.Subject;
import java.util.List;

@Data
public class SlotUpdateDto {
    public Integer id;
    public Integer day;
    public Integer time;
    public Boolean lection;
    public String room;
    public Integer week;
}
