package com.springproj.schedulebuilder.repository.queries;

public class SlotQueries {
    public final String getCreateSlot = "INSERT INTO SLOTS " +
            "(LECTION, ROOM, DAY_ID, TIME_ID) " +
            "VALUES (?, ?, ?, ?) ";
    public final String getCreateSlotSubject = "INSERT INTO SUBJECT_SLOTS " +
            "(SUBJECT_ID, SLOT_ID, WEEK) " +
            "VALUES (?, ?, ?) ";
    public final String getUpdateSlot = "UPDATE SLOTS " +
            "SET LECTION = ?, ROOM = ?, DAY_ID = ?, TIME_ID = ? " +
            "WHERE ID = ?;";
    public final String deleteAllSubjectSlots = "DELETE FROM SUBJECT_SLOTS " +
            "WHERE SLOT_ID = ?;";
    public final String deleteSlot = "DELETE FROM SLOTS " +
            "WHERE ID = ?";
}
