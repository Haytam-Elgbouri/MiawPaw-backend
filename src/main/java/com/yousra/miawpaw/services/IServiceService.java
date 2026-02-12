package com.yousra.miawpaw.services;

import com.yousra.miawpaw.dtos.PetDTO;
import com.yousra.miawpaw.dtos.VetServiceDTO;

import java.util.List;
import java.util.UUID;

public interface IServiceService {
    VetServiceDTO addService(VetServiceDTO dto);
    VetServiceDTO updateService(VetServiceDTO dto, UUID id);
    VetServiceDTO getServiceById(UUID id);
    List<VetServiceDTO> getAllServices();
    void deleteServiceById(UUID id);
}
