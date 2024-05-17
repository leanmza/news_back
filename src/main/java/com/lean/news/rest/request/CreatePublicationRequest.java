package com.lean.news.rest.request;

import com.lean.news.model.entity.User;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CreatePublicationRequest {

    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank
    private String title;

    @NotNull(message = "El cuerpo no puede ser nulo")
    @NotBlank
    private String body;

    @NotNull(message = "El encabezado no puede ser nulo")
    @Size(max = 200, message = "El encabezado no puede tener más de 200 caracteres")
    @NotBlank
    private String header;

    @NotNull(message = "La categoría no puede ser nula")
    @NotBlank
    private String category;

    @Nullable
    private boolean subscriberContent;

    @NotNull(message = "El autor no puede ser nulo")
    @NotBlank
    private User userCreator;


}
