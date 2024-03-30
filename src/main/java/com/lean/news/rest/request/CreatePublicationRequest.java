package com.lean.news.rest.request;


import com.lean.news.enums.CategoryEnum;
import com.lean.news.model.entity.Category;
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
    private String title;

    @NotNull(message = "El cuerpo no puede ser nulo")
    @NotBlank
    private String body;

    @NotNull(message = "La categoría no puede ser nula")
    @NotBlank
    private String category;

    @Nullable
    private boolean subscriberContent;

}
