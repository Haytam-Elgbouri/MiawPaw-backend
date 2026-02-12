package com.yousra.miawpaw.services.Impl;

import com.yousra.miawpaw.dtos.VetServiceDTO;
import com.yousra.miawpaw.entities.VetService;
import com.yousra.miawpaw.mappers.VetServiceMapper;
import com.yousra.miawpaw.repository.VetServiceRepository;
import com.yousra.miawpaw.services.IServiceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceServiceImpl implements IServiceService {

    private final VetServiceMapper vetServiceMapper;
    private final VetServiceRepository vetServiceRepository;

    @Override
    public VetServiceDTO addService(VetServiceDTO dto) {
        VetService pet = vetServiceMapper.toEntity(dto);
        VetService savedVetService = vetServiceRepository.save(pet);
        return vetServiceMapper.toDto(savedVetService);
    }

    @Override
    public VetServiceDTO updateService(VetServiceDTO dto, UUID id) {
        return null;
    }

    @Override
    public VetServiceDTO getServiceById(UUID id) {
        VetService pet = vetServiceRepository.findById(id).orElseThrow(null);
        return vetServiceMapper.toDto(pet);
    }

    @Override
    public List<VetServiceDTO> getAllServices() {
        return vetServiceRepository.findAll().stream()
                .map(vetServiceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteServiceById(UUID id) {
        vetServiceRepository.deleteById(id);
    }
}
