package com.lean.news.model.mapper;

import com.lean.news.dto.request.UserRequestDTO;
import com.lean.news.dto.response.UserResponseDTO;
import com.lean.news.model.entity.UserSec;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "rolesList", ignore = true)
    UserSec toUser (UserRequestDTO userRequestDTO);

    UserResponseDTO toUserResponse(UserSec userSec);

    List<UserResponseDTO> toListUserResponse (List<UserSec> userSecs);
}
