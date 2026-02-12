package com.yousra.miawpaw.repository;

import com.yousra.miawpaw.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PetRepository extends JpaRepository<Pet, UUID> {
}
