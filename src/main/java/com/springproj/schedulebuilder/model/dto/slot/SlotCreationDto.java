package com.springproj.schedulebuilder.model.dto.slot;

import com.springproj.schedulebuilder.model.domain.subject.Subject;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SlotCreationDto {
    public Integer day;
    public Integer time;
    public Integer subject;
    public Boolean lection;
    public String room;
    public Integer week;
}
