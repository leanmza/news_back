package com.lean.news.model.mapper;

import com.lean.news.model.entity.Commentary;
import com.lean.news.rest.request.CreateCommentaryRequest;
import com.lean.news.rest.response.CommentaryResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-26T10:53:26-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class CommentaryMapperImpl implements CommentaryMapper {

    @Override
    public Commentary toCommentary(CreateCommentaryRequest createCommentaryRequest) {
        if ( createCommentaryRequest == null ) {
            return null;
        }

        Commentary commentary = new Commentary();

        commentary.setCommentary( createCommentaryRequest.getCommentary() );

        return commentary;
    }

    @Override
    public List<CommentaryResponse> toListCommentaryResponse(List<Commentary> commentaryList) {
        if ( commentaryList == null ) {
            return null;
        }

        List<CommentaryResponse> list = new ArrayList<CommentaryResponse>( commentaryList.size() );
        for ( Commentary commentary : commentaryList ) {
            list.add( toCommentaryResponse( commentary ) );
        }

        return list;
    }

    @Override
    public CommentaryResponse toCommentaryResponse(Commentary commentary) {
        if ( commentary == null ) {
            return null;
        }

        CommentaryResponse commentaryResponse = new CommentaryResponse();

        commentaryResponse.setUser( commentary.getUser() );
        commentaryResponse.setPublication( commentary.getPublication() );
        commentaryResponse.setId( commentary.getId() );
        commentaryResponse.setCommentary( commentary.getCommentary() );
        commentaryResponse.setDate( commentary.getDate() );

        return commentaryResponse;
    }
}
