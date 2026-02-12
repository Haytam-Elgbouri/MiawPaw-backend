package com.yousra.miawpaw.repository;

import com.yousra.miawpaw.entities.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, UUID> {

    List<TimeSlot> findByDateAndIsAvailableFalse(LocalDate date);

    Optional<TimeSlot> findByDateAndTime(LocalDate date, LocalTime time);

    boolean existsByDate(LocalDate date);
}
