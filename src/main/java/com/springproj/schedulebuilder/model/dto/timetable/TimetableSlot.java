package com.springproj.schedulebuilder.model.dto.timetable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TimetableSlot {
    String time;
    String subject;
    String teacher;
    String group;
    String room;
    Integer week;
}
