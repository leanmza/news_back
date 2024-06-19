package com.lean.news.rest.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ListCommentaryResponse {
    List<CommentaryResponse> commentarys;

}
