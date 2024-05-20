package com.lean.news.service;


import com.lean.news.exception.EntityNotFound;
import com.lean.news.model.entity.Category;
import com.lean.news.model.entity.Image;
import com.lean.news.model.entity.Publication;
import com.lean.news.model.mapper.PublicationMapper;
import com.lean.news.model.repository.PublicationRepository;
import com.lean.news.rest.request.CreatePublicationRequest;
import com.lean.news.rest.request.UpdatePublicationRequest;
import com.lean.news.rest.response.ListPublicationResponse;
import com.lean.news.rest.response.PublicationResponse;
import com.lean.news.service.interfaces.IPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PublicationService implements IPublicationService {

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private PublicationMapper publicationMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    ImageService imageService;


    @Override
    public ResponseEntity<?> create(CreatePublicationRequest createPublicationRequest, List<MultipartFile> images) {

        Publication publication = publicationMapper.toPublication(createPublicationRequest);
        Category category = categoryService.findCategoryByName(createPublicationRequest.getCategory());

        publication.setCategory(category);
        publication.setViews(0);
        publication.setDeleted(false);
        publication.setCreationDate(LocalDateTime.now());
        publication.setAuthor(userService.getUserLogged());
        publicationRepository.save(publication);

        List<Image> imagesList = imageHandler(images, publication);


        PublicationResponse publicationResponse = publicationMapper.toPublicationResponse(publication);
        publication.setImages(imagesList);
        return ResponseEntity.status(HttpStatus.CREATED).body(publicationResponse);
    }


    @Override
    public void delete(String id) {
        publicationRepository.deleteById(id);
    }

    @Override
    public void changeDeletedStatus(String id) {
        Publication publication = findById(id);
        if (publication.isDeleted()) {
            publication.setDeleted(false);
        } else {
            publication.setDeleted(true);
        }
        publicationRepository.save(publication);

    }

    @Override
    public ListPublicationResponse listAllPublications() {
        List<Publication> listPublications = publicationRepository.findAll();
        if (listPublications.isEmpty()) {
            throw new EntityNotFound("No hay publicaciones");
        } else {
            ListPublicationResponse listPublicationResponse = new ListPublicationResponse();
            listPublicationResponse.setPublications(publicationMapper.toListPublicationResponse(listPublications));
            return listPublicationResponse;
        }
    }

    @Override
    public ListPublicationResponse listLastPublications() {
        List<Publication> listPublications = publicationRepository.findLastPublicationByCategory();
        if (listPublications.isEmpty()) {
            throw new EntityNotFound("No hay publicaciones");
        } else {
            ListPublicationResponse listPublicationResponse = new ListPublicationResponse();
            listPublicationResponse.setPublications(publicationMapper.toListPublicationResponse(listPublications));
            return listPublicationResponse;
        }
    }

    @Override
    public ListPublicationResponse listActivePublications() {
        List<Publication> listPublications = publicationRepository.findActivePublications();
        if (listPublications.isEmpty()) {
            throw new EntityNotFound("No hay publicaciones");
        } else {
            ListPublicationResponse listPublicationResponse = new ListPublicationResponse();
            listPublicationResponse.setPublications(publicationMapper.toListPublicationResponse(listPublications));
            return listPublicationResponse;
        }
    }


    @Override
    public ResponseEntity<?> update(String id, UpdatePublicationRequest updatePublicationRequest, List<MultipartFile> images) {
        //Edita una publicación.
        Publication publication = findById(id);
        Publication publicationUpdate = updateValues(updatePublicationRequest, publication);

        publicationRepository.save(publicationUpdate);

        PublicationResponse publicationResponse = publicationMapper.toPublicationResponse(publication);
        if (images != null) {
            List<Image> imageList = imageHandler(images, publication);
            publicationResponse.setImages(imageList);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(publicationResponse);
    }

    @Override
    public ListPublicationResponse findByTitle(String title) {
        List<Publication> listPublications = publicationRepository.findTitleByTitle(title);
        if (listPublications.isEmpty()) {
            throw new EntityNotFound("No hay publicaciones con ese título");
        } else {
            ListPublicationResponse listPublicationResponse = new ListPublicationResponse();
            listPublicationResponse.setPublications(publicationMapper.toListPublicationResponse(listPublications));
            return listPublicationResponse;
        }
    }

    @Override
    public ListPublicationResponse findByAuthor(String author) {
        List<Publication> listPublications = publicationRepository.findByAuthor(author);
        if (listPublications.isEmpty()) {
            throw new EntityNotFound("No hay publicaciones con ese autor");
        } else {
            ListPublicationResponse listPublicationResponse = new ListPublicationResponse();
            listPublicationResponse.setPublications(publicationMapper.toListPublicationResponse(listPublications));
            return listPublicationResponse;
        }

    }

    @Override
    public PublicationResponse updateView(String id) {
        Publication publication = findById(id);
        Publication publicationUpdate = addView(publication);
        publicationRepository.save(publication);
        return publicationMapper.toPublicationResponse(publicationUpdate);
    }

    @Override
    public PublicationResponse getOnePublicationById(String id) {
        Publication publication = findById(id);
        return publicationMapper.toPublicationResponse(publication);
    }

    @Override
    public void deleteImage(String imageId) {
        imageService.delete(imageId);
    }


    @Override
    public List<String> arrangeImages(List<Image> imagesList) {

        List<String> imageList = new ArrayList<>();
        for (Image image : imagesList) {
            imageList.add(image.getId());
        }
        return imageList;
    }

    private List<Image> getImageList(List<String> idList, Publication publication) {

        List<Image> imageList = new ArrayList<>();

        for (String idImage : idList) {

            Image oldImage = imageService.getOne(idImage).get();

            Image newImage = new Image();
            newImage.setName(oldImage.getName());
            newImage.setImageUrl(oldImage.getImageUrl());
            newImage.setCloudinaryId(oldImage.getCloudinaryId());
            newImage.setPublication(publication);

            deleteImage(idImage);

            imageService.save(newImage);

            imageList.add(newImage);
        }

        return imageList;
    }

    private Publication addView(Publication publication) {
        publication.setViews(publication.getViews() + 1);
        return publication;
    }


    private Publication findById(String id) {
        Optional<Publication> optionalPublication = publicationRepository.findById(id);
        if (optionalPublication.isEmpty()) {
            throw new EntityNotFound("La publicación no existe");
        } else {
            return optionalPublication.get();
        }
    }


    private Publication updateValues(UpdatePublicationRequest updatePublicationRequest, Publication publication) {
        String title = updatePublicationRequest.getTitle();
        if (title != null) {
            publication.setTitle(title);
        }

        String body = updatePublicationRequest.getBody();
        if (body != null) {
            publication.setBody(body);
        }

        String category = updatePublicationRequest.getCategory();
        if (category != null) {

            publication.setCategory(getCategory(category));
        }

        boolean subscriberContent = updatePublicationRequest.isSubscriberContent();
        if (publication.isSubscriberContent() == false && subscriberContent == true) {
            publication.setSubscriberContent(true);
        } else if (publication.isSubscriberContent() == true && subscriberContent == false) {
            publication.setSubscriberContent(false);
        }
        return publication;
    }

    private Category getCategory(String name) {

        Category category = categoryService.findCategoryByName(name);

        return category;
    }

    private List<Image> imageHandler(List<MultipartFile> images, Publication publication) {

        List<Image> imageList = new ArrayList<>();

        for (MultipartFile image : images) {
            try {
                Map cloudinaryResult = cloudinaryService.upload(image);
                String imageUrl = (String) cloudinaryResult.get("url");

                Image imageNew = new Image();
                imageNew.setName(image.getOriginalFilename());
                imageNew.setImageUrl(imageUrl);
                imageNew.setCloudinaryId((String) cloudinaryResult.get("public_id"));
                imageNew.setPublication(publication);
                imageService.save(imageNew);

                imageList.add(imageNew);
            } catch (IOException e) {

                return (List<Image>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

        }
        return imageList;
    }
}
