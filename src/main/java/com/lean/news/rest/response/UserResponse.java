package com.lean.news.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lean.news.enums.Rol;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private String id;

    private String name;

    private String lastName;

    private String email;

    private String password;

    private Rol rol;

    private boolean active;
}
