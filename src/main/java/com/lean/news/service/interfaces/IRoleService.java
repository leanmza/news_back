package com.lean.news.service.interfaces;

import com.lean.news.model.entity.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    List<Role> findAll();

    Optional<Role> findById(Long id);

    Role save(Role role);

    Role update(Long id, Role role);

    void delete(Long id);

}
