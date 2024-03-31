package com.lean.news.service.interfaces;

import com.lean.news.rest.request.CreatePublicationRequest;
import com.lean.news.rest.request.UpdatePublicationRequest;
import com.lean.news.rest.response.ListPublicationResponse;
import com.lean.news.rest.response.PublicationResponse;
import org.springframework.http.ResponseEntity;

public interface IPublicationService {
    ResponseEntity<?> create(CreatePublicationRequest createPublicationRequest);

    void delete (String id);

    ListPublicationResponse listAllPublications();

    PublicationResponse update(String id, UpdatePublicationRequest updatePublicationRequest);

   ListPublicationResponse findByTitle(String title);

   ListPublicationResponse findByAuthor(String author);

    PublicationResponse updateView(String id);

    PublicationResponse getOnePublicationById(String id);

}
