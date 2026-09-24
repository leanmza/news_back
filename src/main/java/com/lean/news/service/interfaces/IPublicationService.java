package com.lean.news.service.interfaces;

import com.lean.news.dto.request.PublicationRequestDTO;
import com.lean.news.dto.response.ListPublicationResponse;
import com.lean.news.dto.response.PublicationResponse;
import com.lean.news.model.entity.Publication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPublicationService {

    List<PublicationResponse> findAllActivePublications();


    PublicationResponse findPublicationById(Long id);

    PublicationResponse save(PublicationRequestDTO publicationRequestDTO, List<MultipartFile> images);

    PublicationResponse update(Long id, PublicationRequestDTO publicationRequestDTO, List<MultipartFile> images);
    
    void delete (Long id);

    List<PublicationResponse> findAllPublications();

    List<PublicationResponse> findLastPublications();

    void changeDeletedStatus(Long id);

    void deleteImage (Long imageUrl);

    ResponseEntity<?> arrangeImages(Long id, List<Long> idList);

    Publication addView(Publication publication);

}
