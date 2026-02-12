package com.yousra.miawpaw.controllers;

import com.yousra.miawpaw.entities.TimeSlot;
import com.yousra.miawpaw.services.ITimeSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("/api/booked-slots")
@RequiredArgsConstructor
public class TimeSlotController {

    private final ITimeSlotService timeSlotService;

    @GetMapping
//            ("/{date}")
    public List<TimeSlot> getBookedSlots(@RequestParam LocalDate date){
        return timeSlotService.getAvailableSlots(date);
    }

}
