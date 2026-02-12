package com.yousra.miawpaw.services.Impl;

import com.yousra.miawpaw.dtos.AccessoryDTO;
import com.yousra.miawpaw.entities.Accessory;
import com.yousra.miawpaw.mappers.AccessoryMapper;
import com.yousra.miawpaw.repository.AccessoryRepository;
import com.yousra.miawpaw.services.IAccessoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AccessoryServiceImpl implements IAccessoryService {

    private final AccessoryMapper accessoryMapper;
    private final AccessoryRepository accessoryRepository;

    @Override
    public AccessoryDTO addAccessory(AccessoryDTO dto) {
        Accessory accessory = accessoryMapper.toEntity(dto);
        Accessory savedAccessory = accessoryRepository.save(accessory);
        return accessoryMapper.toDto(savedAccessory);
    }

    @Override
    public AccessoryDTO updateAccessory(AccessoryDTO dto, UUID id) {
        return null;
    }

    @Override
    public AccessoryDTO getAccessoryById(UUID id) {
        Accessory accessory = accessoryRepository.findById(id).orElseThrow(null);
        return accessoryMapper.toDto(accessory);
    }

    @Override
    public List<AccessoryDTO> getAllAccessories() {
        return accessoryRepository.findAll().stream()
                .map(accessoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccessoryById(UUID id) {
        accessoryRepository.deleteById(id);
    }
}
