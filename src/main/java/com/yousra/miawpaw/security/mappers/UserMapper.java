package com.yousra.miawpaw.security.mappers;

import com.yousra.miawpaw.security.mappers.GenericMapper;
//import com.yousra.miawpaw.security.models.dtos.CreateUserDto;
//import com.yousra.miawpaw.security.models.dtos.UpdateUserDto;
import com.yousra.miawpaw.security.models.dtos.UserDto;
import com.yousra.miawpaw.security.models.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<User, UserDto> {
    User toEntity(UserDto userDto);
//    User toEntity(CreateUserDto dto);
//    User toEntity(UpdateUserDto dto);
    UserDto toDto(User user);
}
