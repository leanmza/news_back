package com.lean.news.rest.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CreateCommentaryRequest {
    @NotNull(message = "El comentario no puede ser nulo")
    @NotBlank(message = "El comentario no puede estar en blanco")
    private String commentary;

    @NotNull(message = "El id de la publicación no puede ser nulo")
    @NotBlank(message = "El id de la publicación no puede estar en blanco")
    private String idPublication;
}
