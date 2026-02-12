package com.yousra.miawpaw.services.Impl;

import com.yousra.miawpaw.dtos.PetDTO;
import com.yousra.miawpaw.entities.Pet;
import com.yousra.miawpaw.mappers.PetMapper;
import com.yousra.miawpaw.repository.PetRepository;
import com.yousra.miawpaw.services.IPetService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PetServiceImpl implements IPetService {

    private final PetMapper petMapper;
    private final PetRepository petRepository;

    @Override
    public PetDTO addPet(PetDTO dto) {
        Pet pet = petMapper.toEntity(dto);
        Pet savedPet = petRepository.save(pet);
        return petMapper.toDto(savedPet);
    }

    @Override
    public PetDTO updatePet(PetDTO dto, UUID id) {
        return null;
    }

    @Override
    public PetDTO getPetById(UUID id) {
        Pet pet = petRepository.findById(id).orElseThrow(null);
        return petMapper.toDto(pet);
    }

    @Override
    public List<PetDTO> getAllPets() {
        return petRepository.findAll().stream()
                .map(petMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePetById(UUID id) {
        petRepository.deleteById(id);
    }
}
