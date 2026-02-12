package com.yousra.miawpaw.security.controllers;

import com.yousra.miawpaw.security.models.dtos.ChangePasswordDto;
import com.yousra.miawpaw.security.models.dtos.UserDto;
import com.yousra.miawpaw.security.services.IAuthService;
//import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProfileController {
    private final IAuthService authService;

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getProfile() {
        return new ResponseEntity<>(authService.getAuthenticatedUserProfile(), HttpStatus.OK);
    }

    @PutMapping("/change-password")
    public ResponseEntity<Void> changePassword(
            @RequestBody @Validated ChangePasswordDto changePasswordDto
    ) {
        authService.changePassword(changePasswordDto.oldPassword(), changePasswordDto.newPassword());
        return ResponseEntity.noContent().build();
    }

}
