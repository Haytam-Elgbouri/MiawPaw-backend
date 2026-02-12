package com.yousra.miawpaw.services.Impl;

import com.yousra.miawpaw.entities.TimeSlot;
import com.yousra.miawpaw.repository.TimeSlotRepository;
import com.yousra.miawpaw.services.ITimeSlotService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TimeSlotServiceImpl implements ITimeSlotService {

    private final TimeSlotRepository timeSlotRepository;

    private static final LocalTime START_TIME = LocalTime.of(9, 0);
    private static final LocalTime END_TIME = LocalTime.of(17, 0);

    private static final int MAX_DAYS_AHEAD = 30;

    @Override
    public void generateSlotsForDate(LocalDate date) {

        LocalDate today = LocalDate.now();

        if (date.isBefore(today)) {
            throw new IllegalArgumentException("Cannot generate time slots for past dates");
        }

        if (date.isAfter(today.plusDays(MAX_DAYS_AHEAD))) {
            throw new IllegalArgumentException(
                    "Cannot generate time slots more than " + MAX_DAYS_AHEAD + " days ahead"
            );
        }

        if (timeSlotRepository.existsByDate(date)) {
            return;
        }

        List<TimeSlot> slots = new ArrayList<>();

        LocalTime time = START_TIME;
        while (time.isBefore(END_TIME)) {
            slots.add(TimeSlot.builder()
                    .date(date)
                    .time(time)
                    .isAvailable(true)
                    .build()
            );
            time = time.plusHours(1);
        }

        timeSlotRepository.saveAll(slots);
    }

    @Override
    public List<TimeSlot> getAvailableSlots(LocalDate date) {

//        generateSlotsForDate(date);
        return timeSlotRepository.findByDateAndIsAvailableFalse(date);
    }
}
