package com.springproj.schedulebuilder.model.dto.subject;

import com.springproj.schedulebuilder.model.domain.slot.Slot;
import com.springproj.schedulebuilder.model.dto.slot.SlotFormatted;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FullSubject {
    Integer subjectId;
    Integer userId;
    String name;
    String lecturer;
    String practitioner;
    List<Slot> slots;
}
