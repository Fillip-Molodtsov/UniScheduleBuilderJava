package com.springproj.schedulebuilder.model.dto.slot;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SlotFormatted {
    String time;
    String subject;
    String teacher;
    String group;
    String room;
    String day;
    Integer week;
}
