package com.lean.news.rest.request;

import com.lean.news.model.entity.User;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatePublicationRequest {

    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank
    private String name;

    @NotNull(message = "El cuerpo no puede ser nulo")
    @NotBlank
    private String body;

    @NotNull(message = "El autor no puede ser nulo")
    @NotBlank
    private String Category;

    @NotNull(message = "La categoría no puede ser nula")
    @NotBlank
    private User author;

    @Nullable
    private boolean subscriberContent;

}
