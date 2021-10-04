package com.springproj.schedulebuilder.model.domain.slot;

import com.springproj.schedulebuilder.model.domain.days.Day;
import com.springproj.schedulebuilder.model.domain.intervals.Interval;
import com.springproj.schedulebuilder.model.domain.subject.Subject;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;


@Data
@Builder
@Table("SLOTS")
public class Slot {
    @Id
    private final Integer id;
    @MappedCollection(idColumn = "ID")
    private Day day;
    @MappedCollection(idColumn = "ID")
    private Interval time;

    @MappedCollection(idColumn = "SLOT_ID", keyColumn = "ID")
    private List<SubjectSlots> subjectSlots;

    private Boolean lection;
    private String room;
    private Integer user_id;
}
