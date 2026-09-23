package com.lean.news.service.interfaces;

import com.lean.news.model.entity.Permission;

import java.util.List;
import java.util.Optional;

public interface IPermissionService {
    List<Permission> findAll();

    Optional<Permission> findById(Long id);

    Permission save(Permission permission);

    Permission update(Long id, Permission permission);

    void delete(Long id);
}
