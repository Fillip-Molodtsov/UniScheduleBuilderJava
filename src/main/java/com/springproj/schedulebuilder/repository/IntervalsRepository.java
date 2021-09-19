package com.springproj.schedulebuilder.repository;

import com.springproj.schedulebuilder.model.domain.intervals.Interval;
import org.springframework.data.repository.CrudRepository;

public interface IntervalsRepository extends CrudRepository<Interval, Integer> {
}
