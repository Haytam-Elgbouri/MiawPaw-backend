package com.yousra.miawpaw.mappers;

import com.yousra.miawpaw.dtos.AppointmentDTO;
import com.yousra.miawpaw.entities.Appointment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface AppointmentMapper {
    Appointment toEntity(AppointmentDTO dto);
    AppointmentDTO toDto(Appointment entity);
}
