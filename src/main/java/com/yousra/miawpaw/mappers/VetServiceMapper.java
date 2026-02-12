package com.yousra.miawpaw.mappers;

import com.yousra.miawpaw.dtos.VetServiceDTO;
import com.yousra.miawpaw.entities.VetService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface VetServiceMapper {
    VetService toEntity(VetServiceDTO dto);
    VetServiceDTO toDto(VetService entity);
}
