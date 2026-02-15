package com.yousra.miawpaw.controllers;

import com.yousra.miawpaw.dtos.PetDTO;
import com.yousra.miawpaw.services.IPetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("/api/pet")
@RequiredArgsConstructor
public class PetController {

    private final IPetService petService;

    @PostMapping
    public PetDTO addPet(@RequestBody PetDTO dto) throws IOException {
        return petService.addPet(dto);
    }

    @PutMapping
    public PetDTO updatePet(@RequestBody PetDTO dto, @PathVariable UUID id){
        return petService.updatePet(dto, id);
    }

    @GetMapping("/{id}")
    public PetDTO getPetById(@PathVariable UUID id){
        return petService.getPetById(id);
    }

    @GetMapping
    public List<PetDTO> getAllPets(){
        return petService.getAllPets();
    }

    @DeleteMapping("/{id}")
    public void deletePetById(@PathVariable UUID id){
        petService.deletePetById(id);
    }
}
