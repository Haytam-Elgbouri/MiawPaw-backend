package com.yousra.miawpaw.entities;



import com.yousra.miawpaw.enums.AppointmentSource;
import com.yousra.miawpaw.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String clientName;

    @Column(nullable = false)
    private String clientPhone;

    private String clientEmail;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentSource source;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "time_slot_id")
    private TimeSlot timeSlot;

    @ManyToOne(optional = false)
    @JoinColumn(name = "service_id")
    private VetService service;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
