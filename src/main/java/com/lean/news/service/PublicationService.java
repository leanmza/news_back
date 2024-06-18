package com.lean.news.service;


import com.lean.news.exception.EntityNotFound;
import com.lean.news.exception.ValidationException;
import com.lean.news.model.entity.Category;
import com.lean.news.model.entity.Commentary;
import com.lean.news.model.entity.Image;
import com.lean.news.model.entity.Publication;
import com.lean.news.model.mapper.PublicationMapper;
import com.lean.news.model.repository.PublicationRepository;
import com.lean.news.rest.request.CreateCommentaryRequest;
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
import java.util.stream.Collectors;

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

        publication.setImages(imagesList);

        PublicationResponse publicationResponse = publicationMapper.toPublicationResponse(publication);
//        publicationResponse.setCommentaries(publication.getCommentaries());
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
    public ResponseEntity<?> update(String id, UpdatePublicationRequest updatePublicationRequest, List<MultipartFile> images, List<String> idImages) {
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

/*    public List<Image> arrangeImages(List<String> imagesId, Publication publication) {
        List<Image> newArrange = new ArrayList<>();

        for (String id : imagesId) {
            Image oldImage = imageService.getOne(id).get();

            //Inicializo una nueva imagen
            Image newImage = new Image();
            //Le cargo los atributos de la imagen vieja sin el id
            newImage.setName(oldImage.getName());
            newImage.setImageUrl(oldImage.getImageUrl());
            newImage.setCloudinaryId(oldImage.getCloudinaryId());
            newImage.setPublication(publication);

            //La cargo a la lista
            newArrange.add(newImage);
            //Guardo la imagen nueva en la Bd
            imageService.save(newImage);
        }

        return newArrange;
    }*/

    private Publication addView(Publication publication) {
        publication.setViews(publication.getViews() + 1);
        return publication;
    }

    public Publication findById(String id) {
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

        imagesValidation(images);

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
            } catch (ValidationException e) {
                // Crear y devolver una respuesta de error con la clave adecuada
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put(e.getField(), e.getMessage());
                return (List<Image>) ResponseEntity.badRequest().body(errorResponse);

            } catch (IOException e) {

                return (List<Image>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

        }
        return imageList;
    }

    private void imagesValidation(List<MultipartFile> images) {
        if (images == null || images.isEmpty()) {
            throw new ValidationException("images", "Debe cargar al menos una imagen");
        }

        List<String> acceptedMimeTypes = Arrays.asList("image/jpeg", "image/png", "image/gif");
        for (MultipartFile image : images) {
            if (image.isEmpty()) {
                System.out.println("imageValidation isEmpty " + image.isEmpty());
                throw new ValidationException("images", "Debe cargar al menos una imagen");
            }

            if (!acceptedMimeTypes.contains(image.getContentType())) {
                throw new ValidationException("images", "Formato de archivo no permitido: " + image.getContentType());
            }
        }
    }

    public void setCommentary(Commentary commentary){
        Publication publication = findById(commentary.getPublication().getId());
        List<Commentary> commentaries = publication.getCommentaries();
        commentaries.add(commentary);
        publication.setCommentaries(commentaries);
        publicationRepository.save(publication);
    }

}
