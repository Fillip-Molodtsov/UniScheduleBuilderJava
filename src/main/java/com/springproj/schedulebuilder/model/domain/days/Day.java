package com.springproj.schedulebuilder.model.domain.days;

import com.springproj.schedulebuilder.model.domain.slot.Slot;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Data
@Builder
@Table("DAYS")
public class Day {
    @Id
    private final Integer id;
    private String value;

    @MappedCollection(keyColumn = "ID", idColumn = "DAY_ID")
    private Set<Slot> slots;
}
