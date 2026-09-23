package com.lean.news.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lean.news.enums.Rol;
import com.lean.news.model.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDTO {

    private String id;

    private String username;

    private String name;

    private String lastName;

    private String email;

    private String password;

    private boolean enabled;

    private Set<Role> rolesList;
}
