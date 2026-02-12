package com.yousra.miawpaw.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
//@AllArgsConstructor
@Setter
public class PetDTO {

    private UUID id;

    private String name;

    private String species;

    private String breed;

    private int age;

    private String description;

    private String imageUrl;

    private boolean isAdopted;
}
