package com.lean.news.rest.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CreateUserRequest {

    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank
    private String name;

    @NotNull(message = "El apellido no puede ser nulo")
    @NotBlank
    private String lastName;

    @NotNull(message = "El email no puede ser nulo")
    @NotBlank
    @Email
    private String email;

    @NotNull(message = "La contraseña no puede ser nula")
    @NotBlank
    private String password;


}
