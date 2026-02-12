package com.yousra.miawpaw.repository;

import com.yousra.miawpaw.entities.Appointment;
import com.yousra.miawpaw.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    List<Appointment> findByStatus(AppointmentStatus status);
    List<Appointment> findByTimeSlotDateAndStatus(LocalDate date, AppointmentStatus status);
}
