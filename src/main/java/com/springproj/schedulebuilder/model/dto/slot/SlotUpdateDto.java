package com.springproj.schedulebuilder.model.dto.slot;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class SlotUpdateDto {
    public Integer id;
    public Integer day;
    public Integer time;
    public Integer subject_id;
    private Integer user_id;
    public Boolean lection;
    public String room;
    public List<Integer> weeks;
}
