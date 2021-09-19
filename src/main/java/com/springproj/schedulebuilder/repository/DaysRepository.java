package com.springproj.schedulebuilder.repository;

import com.springproj.schedulebuilder.model.domain.days.Day;
import org.springframework.data.repository.CrudRepository;

public interface DaysRepository extends CrudRepository<Day, Integer> {
}
