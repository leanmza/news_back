package com.lean.news.rest.request;

import com.lean.news.model.entity.User;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdatePublicationRequest {

    @Nullable
    private String title;

    @Nullable
    private String body;

    @Nullable
    private String Category;

    @Nullable
    private boolean subscriberContent;
}
