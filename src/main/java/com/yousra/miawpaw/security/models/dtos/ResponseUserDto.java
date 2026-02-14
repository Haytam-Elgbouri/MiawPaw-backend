package com.yousra.miawpaw.security.models.dtos;

import com.yousra.miawpaw.security.models.enums.Role;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;

public record ResponseUserDto(
//        String firstName,
//        String lastName,
        String username,
//        String phone,
//        String CIN,
        Role role
//        Boolean isActive
) {
}
