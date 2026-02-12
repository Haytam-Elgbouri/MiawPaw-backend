package com.yousra.miawpaw.services;

import com.yousra.miawpaw.dtos.AppointmentDTO;
import com.yousra.miawpaw.enums.AppointmentSource;
import com.yousra.miawpaw.enums.AppointmentStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IAppointmentService {
    AppointmentDTO addAppointment(AppointmentDTO dto, AppointmentSource source);
    AppointmentDTO updateAppointment(AppointmentDTO dto, UUID id);
    AppointmentDTO getAppointmentById(UUID id);
    List<AppointmentDTO> getAllAppointments();
    List<AppointmentDTO> getAppointmentsByStatus(AppointmentStatus status);
    AppointmentDTO approveAppointment(UUID id);
    AppointmentDTO rejectAppointment(UUID id);

    List<AppointmentDTO> getValidatedAppointmentsByDate(LocalDate date);
}
