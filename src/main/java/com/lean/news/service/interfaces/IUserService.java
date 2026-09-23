package com.lean.news.service.interfaces;

import com.lean.news.dto.request.UserRequestDTO;
import com.lean.news.dto.response.UserResponseDTO;
import com.lean.news.model.entity.UserSec;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<UserResponseDTO> findAll();

    Optional<UserSec> findUserById(Long id);

    UserResponseDTO save(UserRequestDTO userRequestDTO);

    UserResponseDTO update(Long id, UserRequestDTO userRequestDTO);

    void delete(Long id);

//    public Optional<User> findByEmail(String email);

    String encriptPassword(String password);

}
