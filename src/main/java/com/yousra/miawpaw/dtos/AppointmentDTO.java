package com.yousra.miawpaw.dtos;

import com.yousra.miawpaw.entities.TimeSlot;
import com.yousra.miawpaw.entities.VetService;
import com.yousra.miawpaw.enums.AppointmentSource;
import com.yousra.miawpaw.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class AppointmentDTO {

    private UUID id;

    private String clientName;

    private String clientPhone;

    private String clientEmail;

    private AppointmentStatus status;

    private AppointmentSource source;

    private TimeSlot timeSlot;

    private UUID serviceId;

    private LocalDateTime createdAt;
}
