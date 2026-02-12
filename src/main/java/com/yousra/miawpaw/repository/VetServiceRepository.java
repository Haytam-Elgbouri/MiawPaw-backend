package com.yousra.miawpaw.repository;

import com.yousra.miawpaw.entities.VetService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VetServiceRepository extends JpaRepository<VetService, UUID> {
}
