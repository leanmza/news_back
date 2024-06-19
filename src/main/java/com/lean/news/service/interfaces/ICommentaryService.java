package com.lean.news.service.interfaces;

import com.lean.news.model.entity.Publication;
import com.lean.news.rest.request.CreateCommentaryRequest;
import com.lean.news.rest.response.ListCommentaryResponse;
import org.springframework.http.ResponseEntity;

public interface ICommentaryService {
    ResponseEntity<?> create(CreateCommentaryRequest createCommentaryRequest);

    void delete (String id);

    ListCommentaryResponse listCommentary();

    ListCommentaryResponse listCommentaryByPublication(Publication id);

}
