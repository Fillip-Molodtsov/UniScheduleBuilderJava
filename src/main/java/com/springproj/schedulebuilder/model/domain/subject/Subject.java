package com.springproj.schedulebuilder.model.domain.subject;

import com.springproj.schedulebuilder.model.domain.slot.Slot;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Data
@Builder
@Table("SUBJECTS")
public class Subject {
    @Id
    private final Integer id;
    private String name;
    private String lecturer;
    private String practitioner;
}
