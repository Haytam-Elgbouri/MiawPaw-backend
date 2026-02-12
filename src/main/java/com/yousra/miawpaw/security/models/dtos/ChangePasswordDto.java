package com.yousra.miawpaw.security.models.dtos;

//import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

public record ChangePasswordDto(
        @NonNull String oldPassword,
        @NonNull String newPassword
) {
}
