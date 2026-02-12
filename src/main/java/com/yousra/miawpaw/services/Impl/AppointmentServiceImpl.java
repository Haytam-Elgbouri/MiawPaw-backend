package com.yousra.miawpaw.services.Impl;

import com.yousra.miawpaw.dtos.AppointmentDTO;
import com.yousra.miawpaw.entities.Appointment;
import com.yousra.miawpaw.entities.TimeSlot;
import com.yousra.miawpaw.enums.AppointmentSource;
import com.yousra.miawpaw.enums.AppointmentStatus;
import com.yousra.miawpaw.mappers.AppointmentMapper;
import com.yousra.miawpaw.repository.AppointmentRepository;
import com.yousra.miawpaw.repository.TimeSlotRepository;
import com.yousra.miawpaw.repository.VetServiceRepository;
import com.yousra.miawpaw.services.IAppointmentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AppointmentServiceImpl implements IAppointmentService {

    private final AppointmentMapper appointmentMapper;
    private final AppointmentRepository appointmentRepository;
    private final TimeSlotRepository timeSlotRepository;
    private final VetServiceRepository serviceRepository;

    @Override
    public AppointmentDTO addAppointment(AppointmentDTO dto, AppointmentSource source) {
        Appointment appointment = appointmentMapper.toEntity(dto);

        if (appointment.getTimeSlot().getDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Cannot book past dates");
        }

        appointment.setStatus(AppointmentStatus.PENDING);

        TimeSlot timeSlot = appointment.getTimeSlot();
        timeSlot.setIsAvailable(true);
        timeSlotRepository.save(timeSlot);

        if (!timeSlot.getIsAvailable()) {
            throw new IllegalStateException("The selected time slot is not available");
        }

        timeSlot.setIsAvailable(false);

        appointment.setService(serviceRepository.findById(dto.getServiceId()).orElseThrow(
                () -> new EntityNotFoundException("Service not found")));

        if (!appointment.getService().isActive()){
            throw new IllegalStateException("Cannot select a non active service");
        }

        appointment.setCreatedAt(LocalDateTime.now());
        appointment.setSource(source);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.toDto(savedAppointment);
    }

    @Override
    public AppointmentDTO updateAppointment(AppointmentDTO dto, UUID id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));

        if(appointment.getStatus()==AppointmentStatus.APPROVED || appointment.getStatus()==AppointmentStatus.REJECTED){
            throw new IllegalStateException("This appointment is already " + appointment.getStatus().name().toLowerCase());
        }

        if (!appointment.getTimeSlot().getId().equals(dto.getTimeSlot().getId())) {

            appointment.getTimeSlot().setIsAvailable(true);

            if (!dto.getTimeSlot().getIsAvailable()) {
                throw new IllegalStateException("New time slot is not available");
            }

            dto.getTimeSlot().setIsAvailable(false);
            appointment.setTimeSlot(dto.getTimeSlot());
        }

        appointment.setClientName(dto.getClientName());
        appointment.setClientEmail(dto.getClientEmail());
        appointment.setClientPhone(dto.getClientPhone());
        appointment.setService(serviceRepository.findById(dto.getServiceId()).orElseThrow(
                () -> new EntityNotFoundException("Service not found")));

        return appointmentMapper.toDto(appointmentRepository.save(appointment));
    }


    @Override
    public AppointmentDTO getAppointmentById(UUID id) {
        Appointment appointment = appointmentRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Appointment not found"));
        return appointmentMapper.toDto(appointment);
    }

    @Override
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByStatus(AppointmentStatus status) {
        return appointmentRepository.findByStatus(status)
                .stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO approveAppointment(UUID id) {
        Appointment appointment = appointmentRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Appointment not found"));

        if (appointment.getStatus() == AppointmentStatus.APPROVED){
            throw new IllegalStateException("Appointment already approved");
        }
        if (appointment.getStatus() == AppointmentStatus.REJECTED){
            throw new IllegalStateException("Appointment already rejected");
        }

        appointment.setStatus(AppointmentStatus.APPROVED);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.toDto(savedAppointment);
    }

    @Override
    public AppointmentDTO rejectAppointment(UUID id) {
        Appointment appointment = appointmentRepository.getReferenceById(id);

        if (appointment.getStatus() == AppointmentStatus.APPROVED){
            throw new IllegalStateException("Appointment already approved");
        }
        if (appointment.getStatus() == AppointmentStatus.REJECTED){
            throw new IllegalStateException("Appointment already rejected");
        }

        appointment.setStatus(AppointmentStatus.REJECTED);
        appointment.getTimeSlot().setIsAvailable(true);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.toDto(savedAppointment);
    }

    @Override
    public List<AppointmentDTO> getValidatedAppointmentsByDate(LocalDate date){
        return appointmentRepository.findByTimeSlotDateAndStatus(date, AppointmentStatus.APPROVED)
            .stream()
            .map(appointmentMapper::toDto)
            .collect(Collectors.toList());
    }


}
