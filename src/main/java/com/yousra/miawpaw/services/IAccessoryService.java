package com.yousra.miawpaw.services;

import com.yousra.miawpaw.dtos.AccessoryDTO;

import java.util.List;
import java.util.UUID;

public interface IAccessoryService {
    AccessoryDTO addAccessory(AccessoryDTO dto);
    AccessoryDTO updateAccessory(AccessoryDTO dto, UUID id);
    AccessoryDTO getAccessoryById(UUID id);
    List<AccessoryDTO> getAllAccessories();
    void deleteAccessoryById(UUID id);
}
