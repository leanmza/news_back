package com.lean.news.rest.request;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UpdateUserRequest {

    @Nullable
    private String name;

    @Nullable
    private String lastName;

    @Nullable
    private String email;

    @Nullable
    private String password;


}
