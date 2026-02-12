package com.yousra.miawpaw.security.services;

import com.yousra.miawpaw.security.models.dtos.JwtResponseDto;
import com.yousra.miawpaw.security.models.dtos.LoginRequestDto;
import com.yousra.miawpaw.security.models.dtos.UserDto;

public interface IAuthService {
    JwtResponseDto login(LoginRequestDto dto);
    UserDto getAuthenticatedUserProfile();
    void changePassword(String oldPassword, String newPassword);

}
