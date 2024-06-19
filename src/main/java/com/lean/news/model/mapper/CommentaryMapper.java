package com.lean.news.model.mapper;

import com.lean.news.model.entity.Commentary;
import com.lean.news.rest.request.CreateCommentaryRequest;
import com.lean.news.rest.response.CommentaryResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentaryMapper {
    Commentary toCommentary(CreateCommentaryRequest createCommentaryRequest);

    List<CommentaryResponse> toListCommentaryResponse(List<Commentary> commentaryList);

    CommentaryResponse toCommentaryResponse(Commentary commentary);
}
