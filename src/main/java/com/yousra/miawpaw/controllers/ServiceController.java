package com.yousra.miawpaw.controllers;

import com.yousra.miawpaw.dtos.VetServiceDTO;
import com.yousra.miawpaw.services.IServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("/api/service")
@RequiredArgsConstructor
public class ServiceController {

    private final IServiceService serviceService;

    @PostMapping
    public VetServiceDTO addVetService(@RequestBody VetServiceDTO dto){
        return serviceService.addService(dto);
    }

    @PutMapping
    public VetServiceDTO updateVetService(@RequestBody VetServiceDTO dto, @PathVariable UUID id){
        return serviceService.updateService(dto, id);
    }

    @GetMapping("/{id}")
    public VetServiceDTO getVetServiceById(@PathVariable UUID id){
        return serviceService.getServiceById(id);
    }

    @GetMapping
    public List<VetServiceDTO> getAllVetServices(){
        return serviceService.getAllServices();
    }

    @DeleteMapping("/{id}")
    public void deleteVetServiceById(@PathVariable UUID id){
        serviceService.deleteServiceById(id);
    }
}
