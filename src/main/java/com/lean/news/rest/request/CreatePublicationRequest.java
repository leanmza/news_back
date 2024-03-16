package com.lean.news.rest.request;

import com.lean.news.enums.Category;
import com.lean.news.model.entity.User;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.lean.news.enums.Category;

@Data
@NoArgsConstructor
public class CreatePublicationRequest {

    @NotNull(message = "El nombre no puede ser nulo")
    @NotBlank
    private String title;

    @NotNull(message = "El cuerpo no puede ser nulo")
    @NotBlank
    private String body;

    @NotNull(message = "La categoría no puede ser nula")
    @NotBlank
    private Category category;

    @NotNull(message = "El autor no puede ser nulo")
    @NotBlank
    private User author;

    @Nullable
    private boolean subscriberContent;

}
