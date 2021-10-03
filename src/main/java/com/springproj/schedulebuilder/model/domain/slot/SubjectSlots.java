package com.springproj.schedulebuilder.model.domain.slot;

import com.springproj.schedulebuilder.model.domain.subject.Subject;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("SUBJECT_SLOTS")
public class SubjectSlots {
    @Id
    private final Integer id;

    @MappedCollection(idColumn = "ID")
    private final Slot slot;

    @MappedCollection(idColumn = "ID")
    private final Subject subject;

    private final Integer week;
    private Integer user_id;
}
