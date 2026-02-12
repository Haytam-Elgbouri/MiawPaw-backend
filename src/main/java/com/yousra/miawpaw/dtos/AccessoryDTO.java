package com.yousra.miawpaw.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class AccessoryDTO {

    private UUID id;

    private String name;

    private String description;

    private double price;

    private int stock;

    private String imageUrl;

    @JsonProperty("isActive")
    private boolean isActive;
}
