package com.yousra.miawpaw.mappers;

import com.yousra.miawpaw.dtos.AccessoryDTO;
import com.yousra.miawpaw.entities.Accessory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccessoryMapper {
    Accessory toEntity(AccessoryDTO dto);
    AccessoryDTO toDto(Accessory entity);
}
