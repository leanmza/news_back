package com.lean.news.rest.request;

import com.lean.news.model.entity.User;

import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
public class CreatePublicationRequest {

    @NotNull(message = "El título no puede ser nulo")
    @NotBlank(message = "El título no puede estar en blanco")
    private String title;

    @NotNull(message = "El cuerpo no puede ser nulo")
    @NotBlank(message = "El cuerpo no puede estar en blanco")
    private String body;

    @NotNull(message = "El encabezado no puede ser nulo")
    @Size(max = 140, message = "El encabezado no puede tener más de 140 caracteres")
    @NotBlank(message = "El encabezado no puede estar en blanco")
    private String header;

    @NotNull(message = "La categoría no puede ser nula")
    @NotBlank(message = "La categoría no puede estar en blanco")
    private String category;

    @Nullable
    private boolean subscriberContent;

}
