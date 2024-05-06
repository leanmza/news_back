package com.lean.news.rest.response;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
public class ListUsersResponse {

    List<UserResponse> users;
}
