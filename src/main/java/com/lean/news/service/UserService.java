package com.lean.news.service;

import com.lean.news.dto.request.UserRequestDTO;
import com.lean.news.model.entity.Role;
import com.lean.news.model.entity.UserSec;
import com.lean.news.model.mapper.UserMapper;
import com.lean.news.repository.IUserRepository;
import com.lean.news.dto.response.UserResponseDTO;
import com.lean.news.service.interfaces.IRoleService;
import com.lean.news.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.util.*;


@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserResponseDTO> findAll() {
        List<UserSec> listUserSecs = userRepo.findAll();
        return userMapper.toListUserResponse(listUserSecs);
    }

    @Override
    public Optional<UserSec> findUserById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public UserResponseDTO save(UserRequestDTO userRequestDTO) {
        UserSec userSec = userMapper.toUser(userRequestDTO);
        userSec.setEnabled(true);

        userSec.setPassword(encriptPassword((userSec.getPassword())));

        userSec.setRolesList(cargarListaRoles(userRequestDTO.getRolesList()));
        userRepo.save(userSec);


        return userMapper.toUserResponse(userSec);
    }


    @Override
    public UserResponseDTO update(Long id, UserRequestDTO userRequestDTO) {
        UserSec updatedUserSec = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User doesn't exist"));

        updatedUserSec.setName(userRequestDTO.getName());
        updatedUserSec.setLastname(userRequestDTO.getLastname());
        updatedUserSec.setEmail(userRequestDTO.getEmail());
        updatedUserSec.setPassword(encriptPassword(userRequestDTO.getPassword()));
        updatedUserSec.setRolesList(cargarListaRoles(userRequestDTO.getRolesList()));
        userRepo.save(updatedUserSec);
        return userMapper.toUserResponse(updatedUserSec);


    }

    @Override
    public void delete(Long id) {
        UserSec deletedUserSec = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User doesn't exist"));
        deletedUserSec.setEnabled(false);
        userRepo.save(deletedUserSec);
    }


    private Set<Role> cargarListaRoles(@NotEmpty List<Long> rolesList) {
        Set<Role> rolesListSet = new HashSet<>();

        for (Long roleId : rolesList) {
            Role role = roleService.findById(roleId).orElse(null);

            if (role != null) {
                rolesListSet.add(role);
            }
        }

        return rolesListSet;
    }

    @Override
    public String encriptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

}
