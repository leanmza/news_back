package com.lean.news.model.mapper;

import com.lean.news.model.entity.User;
import com.lean.news.rest.request.CreateUserRequest;
import com.lean.news.rest.response.UserResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser (CreateUserRequest createUserRequest);

    UserResponse toUserResponse(User user);

    List<UserResponse> toListUserResponse (List<User> users);
}
