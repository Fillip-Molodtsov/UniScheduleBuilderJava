package com.springproj.schedulebuilder.repository;

import com.springproj.schedulebuilder.model.domain.slot.Slot;
import org.springframework.data.repository.CrudRepository;

public interface SlotRepository extends CrudRepository<Slot, Integer> {
}
