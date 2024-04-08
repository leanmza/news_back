package com.lean.news.service.interfaces;

import com.lean.news.rest.request.CreatePublicationRequest;
import com.lean.news.rest.request.UpdatePublicationRequest;
import com.lean.news.rest.response.ListPublicationResponse;
import com.lean.news.rest.response.PublicationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPublicationService {
    ResponseEntity<?> create (CreatePublicationRequest createPublicationRequest, List<MultipartFile> images);

    void delete (String id);

    ListPublicationResponse listAllPublications();

    ResponseEntity<?> update(String id, UpdatePublicationRequest updatePublicationRequest, List<MultipartFile> images);

   ListPublicationResponse findByTitle(String title);

   ListPublicationResponse findByAuthor(String author);

    PublicationResponse updateView(String id);

    PublicationResponse getOnePublicationById(String id);

}
