package com.lean.news.controller;

import com.lean.news.model.entity.User;
import com.lean.news.rest.request.CreateUserRequest;
import com.lean.news.rest.request.UpdateUserRequest;
import com.lean.news.rest.response.ListUsersResponse;
import com.lean.news.rest.response.UserResponse;
import com.lean.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserRequest createUserRequest) {
        UserResponse userResponse = userService.create(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListUsersResponse> listAllUsers() {
        return ResponseEntity.ok().body(userService.listUsers());
    }

    @GetMapping(value= "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> get() {
        return ResponseEntity.ok().body(userService.getUserLogged());
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable String id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value ="/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> update(@PathVariable String id, @Valid @RequestBody UpdateUserRequest updateUserRequest){
        UserResponse userResponse = userService.update(id, updateUserRequest);
        return ResponseEntity.ok().body(userResponse);
    }
}
