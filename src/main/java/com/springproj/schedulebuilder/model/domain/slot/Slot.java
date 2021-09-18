package com.springproj.schedulebuilder.model.domain.slot;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.security.auth.Subject;

@Data
@Table("SLOTS")
public class Slot {
    @Id
    private final Integer id;
    private String day;
    private String time;
    private Subject subject;
    private Boolean lection;
    private String room;
    private Integer week;
}
