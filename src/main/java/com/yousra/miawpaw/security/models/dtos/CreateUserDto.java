package com.yousra.miawpaw.security.models.dtos;

import com.yousra.miawpaw.security.models.enums.Role;
import org.antlr.v4.runtime.misc.NotNull;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;

public record CreateUserDto(
        String firstName,
        String lastName,

        String email,
        String phone,
        String CIN,
        @NotNull Role role,
        Boolean isActive,
        @NotNull String password
) {
}
