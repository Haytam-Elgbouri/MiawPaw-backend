package com.yousra.miawpaw.services;

import com.yousra.miawpaw.entities.TimeSlot;

import java.time.LocalDate;
import java.util.List;

public interface ITimeSlotService {

    void generateSlotsForDate(LocalDate date);

    List<TimeSlot> getAvailableSlots(LocalDate date);
}
