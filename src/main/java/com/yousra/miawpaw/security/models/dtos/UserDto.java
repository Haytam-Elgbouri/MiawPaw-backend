package com.yousra.miawpaw.security.models.dtos;

import com.yousra.miawpaw.security.models.enums.Role;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;

public record UserDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String CIN,
        Role role,
//        List<OrdrePaiementEmisDto> payments,
        Boolean isActive
) {
}
