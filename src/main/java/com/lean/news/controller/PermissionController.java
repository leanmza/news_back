package com.lean.news.controller;

import com.lean.news.model.entity.Permission;
import com.lean.news.service.interfaces.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/permissions")
@PreAuthorize("denyAll()")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List> getAllPermissions (){
        List<Permission> permissions = permissionService.findAll();
        return ResponseEntity.ok(permissions);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity getPermissionById(@PathVariable Long id){
        Optional<Permission> permission = permissionService.findById(id);
        return permission.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity createPermisssion (@RequestBody Permission permission){
        Permission newPermission = permissionService.save(permission);
        return ResponseEntity.ok(newPermission);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity updatePermission(@PathVariable Long id, @RequestBody Permission permission){
        Permission updatedPermission = permissionService.update(id, permission);
        return ResponseEntity.ok(updatedPermission);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity deletePermission(@PathVariable Long id){
        permissionService.delete(id);
        return ResponseEntity.ok("Permission successfully deleted");
    }
}
