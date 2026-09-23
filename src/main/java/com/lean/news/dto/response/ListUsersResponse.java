package com.lean.news.dto.response;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
public class ListUsersResponse {

    List<UserResponseDTO> users;
}
