package com.lean.news.service.interfaces;

import com.lean.news.model.entity.User;
import com.lean.news.rest.request.CreateUserRequest;
import com.lean.news.rest.request.UpdateUserRequest;
import com.lean.news.rest.response.ListUsersResponse;
import com.lean.news.rest.response.UserResponse;

import java.util.Optional;

public interface IUserService {
    UserResponse create (CreateUserRequest createUserRequest);

    void delete(String id);

    public Optional<User> findByEmail(String email);

    ListUsersResponse listUsers();

    UserResponse update(String id, UpdateUserRequest updateUserRequest);


}
