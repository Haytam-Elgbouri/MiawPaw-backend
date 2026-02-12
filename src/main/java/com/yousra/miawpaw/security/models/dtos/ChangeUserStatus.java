package com.yousra.miawpaw.security.models.dtos;

//import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

public record ChangeUserStatus(
        @NonNull Boolean isActive
) {
}
