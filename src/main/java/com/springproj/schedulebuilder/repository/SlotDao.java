package com.springproj.schedulebuilder.repository;

import com.springproj.schedulebuilder.exception.BadRequestException;
import com.springproj.schedulebuilder.model.domain.slot.Slot;
import com.springproj.schedulebuilder.model.dto.slot.SlotCreationDto;
import com.springproj.schedulebuilder.model.dto.slot.SlotUpdateDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SlotDao {
    Slot findById(Integer id, Integer user_id) throws BadRequestException;

    List<Slot> findByUserSubject(Integer subjectId, Integer userId);

    void save(SlotCreationDto slot, Integer user_id);

    void update(SlotUpdateDto slot);

    void delete(Integer id);
}
