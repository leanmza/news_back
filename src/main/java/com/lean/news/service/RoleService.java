package com.lean.news.service;

import com.lean.news.model.entity.Permission;
import com.lean.news.model.entity.Role;
import com.lean.news.repository.IPermissionRepository;
import com.lean.news.repository.IRoleRepository;
import com.lean.news.service.interfaces.IPermissionService;
import com.lean.news.service.interfaces.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepo;

    @Autowired
    private IPermissionService permissionService;

    @Override
    public List<Role> findAll() {
        return roleRepo.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepo.findById(id);
    }

    @Override
    public Role save(Role role) {
        System.out.println(role);
        role.setPermissionsList(cargarLista(role.getPermissionsList()));
        return roleRepo.save(role);
    }



    @Override
    public Role update(Long id, Role role) {
        Role updatedRole = roleRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Role not found"));
        updatedRole = role;
        updatedRole.setPermissionsList(cargarLista(role.getPermissionsList()));

        return roleRepo.save(updatedRole);
    }

    @Override
    public void delete(Long id) {
        roleRepo.deleteById(id);
    }
    private Set<Permission> cargarLista(Set<Permission> permissions) {
        Set<Permission> permissionList = new HashSet<>();
        Permission readPermission;

        //Recuperar el Permission/s por su ID
        for (Permission permission : permissions) {
            System.out.println(permission);
            readPermission = permissionService.findById(permission.getId()).orElse(null);
            if (readPermission != null) {
                permissionList.add(readPermission);
            }
        }
        return permissionList;
    }
}
