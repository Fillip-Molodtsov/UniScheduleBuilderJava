package com.springproj.schedulebuilder.repository.queries;

import org.springframework.stereotype.Component;

@Component
public class TimetableQueries {
    public final String getTimetableSlots = "SELECT * FROM SUBJECT_SLOTS WHERE USER_ID = ?";
    public final String clearSubjectSlots = "DELETE FROM SUBJECT_SLOTS WHERE USER_ID = ?";
    public final String clearSubjects = "DELETE FROM SUBJECTS WHERE USER_ID = ?";
    public final String clearSlots = "DELETE FROM SLOTS WHERE USER_ID = ?";
}
