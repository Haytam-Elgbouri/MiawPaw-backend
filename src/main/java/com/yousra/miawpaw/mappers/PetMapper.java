package com.yousra.miawpaw.mappers;

import com.yousra.miawpaw.dtos.PetDTO;
import com.yousra.miawpaw.entities.Pet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface PetMapper {
    Pet toEntity(PetDTO dto);
    PetDTO toDto(Pet entity);
}
