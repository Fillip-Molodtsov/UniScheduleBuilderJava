package com.springproj.schedulebuilder.model.domain.days;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("DAYS")
public class Day {
    @Id
    private final Integer id;
    private String value;
}
