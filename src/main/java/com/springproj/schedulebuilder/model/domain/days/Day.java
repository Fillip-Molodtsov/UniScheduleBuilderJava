package com.springproj.schedulebuilder.model.domain.days;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("DAYS")
public class Day {
    @Id
    private final Integer id;
    private String value;
}
