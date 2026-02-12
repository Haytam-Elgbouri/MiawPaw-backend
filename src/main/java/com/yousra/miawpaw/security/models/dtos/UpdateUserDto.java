package com.yousra.miawpaw.security.models.dtos;

import com.yousra.miawpaw.security.models.enums.Role;

public record UpdateUserDto(
        String firstName,
        String lastName,
        String email,
        String phone,
        String CIN,
        Role role
) {
}
