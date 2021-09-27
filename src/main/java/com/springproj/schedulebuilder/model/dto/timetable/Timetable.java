package com.springproj.schedulebuilder.model.dto.timetable;

import com.springproj.schedulebuilder.model.dto.slot.SlotFormatted;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Timetable {
    public String day;
    public List<TimetableSlot> slots;
}
