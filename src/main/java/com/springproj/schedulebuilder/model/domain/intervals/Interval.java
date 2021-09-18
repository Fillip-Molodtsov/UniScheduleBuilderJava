package com.springproj.schedulebuilder.model.domain.intervals;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("INTERVALS")
public class Interval {
    @Id
    private final Integer id;
    private String value;
}
