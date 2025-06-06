package com.lean.news.rest.request;


import jakarta.annotation.Nullable;
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
