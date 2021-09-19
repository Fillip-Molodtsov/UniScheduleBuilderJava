package com.springproj.schedulebuilder.model.domain.slot;

import com.springproj.schedulebuilder.model.domain.days.Day;
import com.springproj.schedulebuilder.model.domain.intervals.Interval;
import com.springproj.schedulebuilder.model.domain.subject.Subject;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;


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
    @MappedCollection(idColumn = "ID")
    private Subject subject;

    private Boolean lection;
    private String room;
    private Integer week;
}
