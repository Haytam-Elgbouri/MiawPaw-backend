package com.yousra.miawpaw.services;

import com.yousra.miawpaw.dtos.AppointmentDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IAppointmentService {
    AppointmentDTO addAppointment(AppointmentDTO dto);
    AppointmentDTO updateAppointment(AppointmentDTO dto, UUID id);
    AppointmentDTO getAppointmentById(UUID id);
    List<AppointmentDTO> getAllAppointments();
    List<AppointmentDTO> getAllApprovedAppointments();
    AppointmentDTO approveAppointment(UUID id);
    AppointmentDTO rejectAppointment(UUID id);

    List<AppointmentDTO> getValidatedAppointmentsByDate(LocalDate date);
}
