package com.springproj.schedulebuilder.model.domain.subject;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

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
