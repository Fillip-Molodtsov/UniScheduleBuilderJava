package com.springproj.schedulebuilder.model.dto.slot;

import com.springproj.schedulebuilder.model.domain.subject.Subject;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SlotCreationDto {
    public Integer day;
    public Integer time;
    public Integer subject;
    public Boolean lection;
    public String room;
    private Integer user_id;
    public List<Integer> weeks;
}
