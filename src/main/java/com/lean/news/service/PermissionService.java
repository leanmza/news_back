package com.lean.news.service;

import com.lean.news.model.entity.Permission;
import com.lean.news.repository.IPermissionRepository;
import com.lean.news.service.interfaces.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService implements IPermissionService {
    @Autowired
    private IPermissionRepository permissionRepo;

    @Override
    public List<Permission> findAll() {
        return permissionRepo.findAll();
    }

    @Override
    public Optional<Permission> findById(Long id) {
        return permissionRepo.findById(id);
    }

    @Override
    public Permission save(Permission permission) {
        return permissionRepo.save(permission);
    }

    @Override
    public Permission update(Long id, Permission permission) {
        Permission updatedPermission = permissionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission doesn't exist"));
        updatedPermission = permission;
        return permissionRepo.save(updatedPermission);
    }

    @Override
    public void delete(Long id) {
        permissionRepo.deleteById(id);
    }
}
