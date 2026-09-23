package com.lean.news.controller;

import com.lean.news.dto.request.UserRequestDTO;
import com.lean.news.dto.response.UserResponseDTO;
import com.lean.news.model.entity.UserSec;
import com.lean.news.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping/*(produces = MediaType.APPLICATION_JSON_VALUE)*/
    public ResponseEntity<List> getAllUsers() {
        List<UserResponseDTO> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable Long id){
        Optional<UserSec> user = userService.findUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping/*(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)*/
    public ResponseEntity createUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        UserResponseDTO newUser = userService.save(userRequestDTO);
        return ResponseEntity.ok(newUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO updatedUser = userService.update(id, userRequestDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok("User successfully deleted");
    }


}
