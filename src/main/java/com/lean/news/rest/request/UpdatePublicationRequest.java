package com.lean.news.rest.request;

import com.lean.news.model.entity.User;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePublicationRequest {

    @Nullable
    private String title;

    @Nullable
    private String body;

    @Nullable
    private String header;

    @Nullable
    private String Category;

    @Nullable
    private boolean subscriberContent;
}
