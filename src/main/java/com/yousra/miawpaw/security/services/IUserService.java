package com.yousra.miawpaw.security.services;

import com.yousra.miawpaw.security.models.dtos.ChangeUserStatus;
import com.yousra.miawpaw.security.models.dtos.CreateUserDto;
import com.yousra.miawpaw.security.models.dtos.UpdateUserDto;
import com.yousra.miawpaw.security.models.dtos.UserDto;
import com.yousra.miawpaw.security.models.entities.User;
import com.yousra.miawpaw.security.services.GenericService;

public interface IUserService extends GenericService<CreateUserDto, UpdateUserDto, UserDto, Long> {
    void changeUserStatus(Long id, ChangeUserStatus changeUserStatus);
    User getUserByEmail(String email);
}
