package com.yousra.miawpaw.services;

import com.yousra.miawpaw.dtos.PetDTO;
import com.yousra.miawpaw.dtos.VetServiceDTO;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface IPetService {
    PetDTO addPet(PetDTO dto) throws IOException;
    PetDTO updatePet(PetDTO dto, UUID id);
    PetDTO getPetById(UUID id);
    List<PetDTO> getAllPets();
    void deletePetById(UUID id);
}
