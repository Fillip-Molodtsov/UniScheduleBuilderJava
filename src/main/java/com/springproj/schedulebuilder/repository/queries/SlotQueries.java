package com.springproj.schedulebuilder.repository.queries;

import org.springframework.stereotype.Component;

@Component
public class SlotQueries {
    public final String getCreateSlot = "INSERT INTO SLOTS " +
            "(LECTION, ROOM, DAY_ID, TIME_ID, USER_ID) " +
            "VALUES (?, ?, ?, ?, ?) ";
    public final String getCreateSlotSubject = "INSERT INTO SUBJECT_SLOTS " +
            "(SUBJECT_ID, SLOT_ID, WEEK, USER_ID) " +
            "VALUES (?, ?, ?, ?) ";
    public final String getUpdateSlot = "UPDATE SLOTS " +
            "SET LECTION = ?, ROOM = ?, DAY_ID = ?, TIME_ID = ?, USER_ID = ?" +
            "WHERE ID = ?;";
    public final String deleteAllSubjectSlots = "DELETE FROM SUBJECT_SLOTS " +
            "WHERE SLOT_ID = ?;";
    public final String deleteSlot = "DELETE FROM SLOTS " +
            "WHERE ID = ?";
    public final String getSlotById = "SELECT * FROM SLOTS WHERE ID = ?";
    public final String getSubjectSlotsBySlotId = "SELECT * FROM SUBJECT_SLOTS WHERE SLOT_ID = ?";
    public final String getSlotsBySubject = "SELECT DISTINCT SLOT_ID\n" +
            "FROM SUBJECT_SLOTS SS\n" +
            "WHERE SS.SUBJECT_ID = ? AND SS.USER_ID = ?";
}
