package com.lean.news.service.interfaces;

import com.lean.news.model.entity.Image;
import com.lean.news.model.entity.Publication;
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

    void changeDeletedStatus(String id);

    ListPublicationResponse listAllPublications();


    ListPublicationResponse listActivePublications();

    ListPublicationResponse listLastPublications();

    ResponseEntity<?> update(String id, UpdatePublicationRequest updatePublicationRequest, List<MultipartFile> images, List<String> idImages);

   ListPublicationResponse findByTitle(String title);

   ListPublicationResponse findByAuthor(String author);

    PublicationResponse updateView(String id);

    PublicationResponse getOnePublicationById(String id);

    void deleteImage (String imageUrl);

//    List<Image> arrangeImages(List<String> idImages, Publication publication);
}
