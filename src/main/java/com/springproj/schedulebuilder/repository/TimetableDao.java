package com.springproj.schedulebuilder.repository;

import com.springproj.schedulebuilder.model.domain.slot.SubjectSlots;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TimetableDao {
    List<SubjectSlots> getSubjectSlots(Integer user_id);

    void clear(Integer user_id);
}
