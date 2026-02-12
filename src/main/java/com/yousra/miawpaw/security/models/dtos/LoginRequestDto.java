package com.yousra.miawpaw.security.models.dtos;

public record LoginRequestDto(
        String email,
        String password
) {
}
