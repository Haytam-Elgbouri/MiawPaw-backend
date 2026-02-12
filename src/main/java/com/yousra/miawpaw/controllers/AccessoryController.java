package com.yousra.miawpaw.controllers;

import com.yousra.miawpaw.dtos.AccessoryDTO;
import com.yousra.miawpaw.services.IAccessoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("/api/accessory")
@RequiredArgsConstructor
public class AccessoryController {

    private final IAccessoryService accessoryService;

    @PostMapping
    public AccessoryDTO addAccessory(@RequestBody AccessoryDTO dto){
        return accessoryService.addAccessory(dto);
    }

    @PutMapping
    public AccessoryDTO updateAccessory(@RequestBody AccessoryDTO dto, @PathVariable UUID id){
        return accessoryService.updateAccessory(dto, id);
    }

    @GetMapping("/{id}")
    public AccessoryDTO getAccessoryById(@PathVariable UUID id){
        return accessoryService.getAccessoryById(id);
    }

    @GetMapping
    public List<AccessoryDTO> getAllAccessories(){
        return accessoryService.getAllAccessories();
    }

    @DeleteMapping("/{id}")
    public void deleteAccessoryById(@PathVariable UUID id){
        accessoryService.deleteAccessoryById(id);
    }
}
