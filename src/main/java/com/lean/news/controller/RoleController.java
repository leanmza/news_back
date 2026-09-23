package com.lean.news.controller;

import com.lean.news.model.entity.Role;
import com.lean.news.service.interfaces.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @GetMapping
    public ResponseEntity<List> getAllRoles(){
        List<Role> roles = roleService.findAll();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity getRoleById(@PathVariable Long id){
        Optional<Role> roles = roleService.findById(id);
        return roles.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity createRole(@RequestBody Role role){
        System.out.println(role);
        Role newRole = roleService.save(role);

        return ResponseEntity.ok(newRole);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateRole(@PathVariable Long id, @RequestBody Role role){
        Role updatedRole = roleService.update(id, role);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRole(@PathVariable Long id){
        roleService.delete(id);
        return ResponseEntity.ok("Role successfully deleted");
    }
}
