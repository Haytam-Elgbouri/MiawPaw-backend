package com.yousra.miawpaw.security.models.dtos;

import com.yousra.miawpaw.security.models.enums.Role;

public record JwtResponseDto(
        String token,
        Long id,
        String username,
        Role role
) {
}
