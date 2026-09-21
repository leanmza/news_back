package com.lean.news.service.interfaces;

import com.lean.news.model.entity.Image;
import com.lean.news.rest.request.CreatePublicationRequest;
import com.lean.news.rest.request.UpdatePublicationRequest;
import com.lean.news.rest.response.ListPublicationResponse;
import com.lean.news.rest.response.PublicationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPublicationService {
    ResponseEntity<?> create (CreatePublicationRequest createPublicationRequest, List<MultipartFile> images);

    void delete (Long id);

    void changeDeletedStatus(Long id);

    ListPublicationResponse listAllPublications();


    ListPublicationResponse listActivePublications();

    ListPublicationResponse listLastPublications();

    ResponseEntity<?> update(Long id, UpdatePublicationRequest updatePublicationRequest, List<MultipartFile> images);

   ListPublicationResponse findByTitle(String title);

   ListPublicationResponse findByAuthor(String author);

    PublicationResponse updateView(Long id);

    PublicationResponse getOnePublicationById(Long id);

    void deleteImage (Long imageUrl);

    ResponseEntity<?> arrangeImages(Long id, List<Long> idList);
}
