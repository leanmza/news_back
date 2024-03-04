package com.lean.news.service.interfaces;

import com.lean.news.rest.request.CreatePublicationRequest;
import com.lean.news.rest.request.UpdatePublicationRequest;
import com.lean.news.rest.response.ListPublicationResponse;
import com.lean.news.rest.response.PublicationResponse;

public interface IPublicationService {
    PublicationResponse create(CreatePublicationRequest createPublicationRequest);

    void delete (String id);

    ListPublicationResponse listAllPublications();

    PublicationResponse update(String id, UpdatePublicationRequest updatePublicationRequest);

    public ListPublicationResponse findByTitle(String title);

    public ListPublicationResponse findByAuthor(String author);

//    public ListPublicationResponse findByCategory(String category);

}
