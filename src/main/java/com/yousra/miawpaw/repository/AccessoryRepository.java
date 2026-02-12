package com.yousra.miawpaw.repository;

import com.yousra.miawpaw.entities.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccessoryRepository extends JpaRepository<Accessory, UUID> {
}
