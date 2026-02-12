package com.yousra.miawpaw.controllers;

import com.yousra.miawpaw.dtos.AppointmentDTO;
import com.yousra.miawpaw.enums.AppointmentSource;
import com.yousra.miawpaw.enums.AppointmentStatus;
import com.yousra.miawpaw.services.IAppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("/api/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final IAppointmentService appointmentService;

    @PostMapping("/online")
    public AppointmentDTO addAppointmentOnline(@RequestBody AppointmentDTO dto){
        return appointmentService.addAppointment(dto, AppointmentSource.ONLINE);
    }
    @PostMapping("/phone")
    public AppointmentDTO addAppointmentPhone(@RequestBody AppointmentDTO dto){
        return appointmentService.addAppointment(dto, AppointmentSource.PHONE);
    }

    @PutMapping("/{id}")
    public AppointmentDTO updateAppointment(@RequestBody AppointmentDTO dto, @PathVariable UUID id){
        return appointmentService.updateAppointment(dto, id);
    }

    @GetMapping
    public List<AppointmentDTO> getAllAppointments(){
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public AppointmentDTO getAppointmentById(@PathVariable UUID id){
        return appointmentService.getAppointmentById(id);
    }

    @PutMapping("/approve/{id}")
    public AppointmentDTO approveAppointment(@PathVariable UUID id){
        return appointmentService.approveAppointment(id);
    }

    @PutMapping("/reject/{id}")
    public AppointmentDTO rejectAppointment(@PathVariable UUID id){
        return appointmentService.rejectAppointment(id);
    }

    @GetMapping("/validated-appointments")
    public List<AppointmentDTO> getValidatedAppointmentsByDate(@RequestParam LocalDate date){
        return appointmentService.getValidatedAppointmentsByDate(date);
    }

    @GetMapping("/approved")
    public List<AppointmentDTO> getApprovedAppointments(){
        return appointmentService.getAppointmentsByStatus(AppointmentStatus.APPROVED);
    }

    @GetMapping("/rejected")
    public List<AppointmentDTO> getRejectedAppointments(){
        return appointmentService.getAppointmentsByStatus(AppointmentStatus.REJECTED);
    }

    @GetMapping("/pending")
    public List<AppointmentDTO> getPendingAppointments(){
        return appointmentService.getAppointmentsByStatus(AppointmentStatus.PENDING);
    }
}
