package com.lean.news.service;


import com.lean.news.dto.request.PublicationRequestDTO;
import com.lean.news.exception.EntityNotFound;
import com.lean.news.model.entity.Category;
import com.lean.news.model.entity.Image;
import com.lean.news.model.entity.Publication;
import com.lean.news.model.mapper.PublicationMapper;
import com.lean.news.repository.IPublicationRepository;
import com.lean.news.dto.response.ListPublicationResponse;
import com.lean.news.dto.response.PublicationResponse;
import com.lean.news.service.interfaces.IAuthService;
import com.lean.news.service.interfaces.ICategoryService;
import com.lean.news.service.interfaces.IPublicationService;
import com.lean.news.service.interfaces.IUserService;
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
    private IPublicationRepository publicationRepo;

    @Autowired
    private PublicationMapper publicationMapper;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IAuthService authService;

    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    ImageService imageService;

    @Override
    public List<PublicationResponse> findAllActivePublications() {
        List<Publication> listPublications = publicationRepo.findActivePublications();
        return publicationMapper.toListPublicationResponse(listPublications);
    }

    @Override
    public PublicationResponse findPublicationById(Long id) {
        Publication publication = findById(id);
        addView(publication);
        return publicationMapper.toPublicationResponse(publication);

    }

    @Override
    public PublicationResponse save(PublicationRequestDTO publicationRequestDTO, List<MultipartFile> images) {

        Publication publication = publicationMapper.toPublication(publicationRequestDTO);

        publication.setCategory(getCategory(publicationRequestDTO.getCategory()));
        publication.setCreationDate(LocalDateTime.now());

        publication.setAuthor(userService.findByUsername(authService.getUserName())
                .orElseThrow(() -> new RuntimeException("User doesn't exist")));

        publicationRepo.save(publication);

        List<Image> imagesList = imageHandler(images, publication);

        publication.setImages(imagesList);
        return publicationMapper.toPublicationResponse(publication);
    }

    @Override
    public PublicationResponse update(Long id, PublicationRequestDTO publicationRequestDTO, List<MultipartFile> images) {

        Publication updatedPublication = findById(id);

        updatedPublication.setTitle(publicationRequestDTO.getTitle());
        updatedPublication.setHeader(publicationRequestDTO.getHeader());
        updatedPublication.setBody(publicationRequestDTO.getBody());
        updatedPublication.setCategory(getCategory(publicationRequestDTO.getCategory()));

        publicationRepo.save(updatedPublication);

        PublicationResponse publicationResponse = publicationMapper.toPublicationResponse(updatedPublication);
        if (images != null) {
            List<Image> imageList = imageHandler(images, updatedPublication);
            publicationResponse.setImages(imageList);
        }

        return publicationResponse;
    }

    @Override
    public void delete(Long id) {
        publicationRepo.deleteById(id);
    }

    @Override
    public List<PublicationResponse> findLastPublications() {
        List<Publication> listPublications = publicationRepo.findLastPublicationByCategory();
        return publicationMapper.toListPublicationResponse(listPublications);
    }

    @Override
    public List<PublicationResponse> findAllPublications() {
        List<Publication> listPublications = publicationRepo.findAll();
        return publicationMapper.toListPublicationResponse(listPublications);
    }

    @Override
    public void deleteImage(Long imageId) {
        imageService.delete(imageId);
    }

    @Override
    public ResponseEntity<?> arrangeImages(Long id, List<Long> imagesId) {

        Publication publication = findById(id);

        // Obtener la lista de imágenes actual de la publicación
        List<Image> currentImages = publication.getImages();
        System.out.println("antes de ordenar");
        for (Image image : currentImages) {
            System.out.println(image);
        }
        // Mapa para acceder rápidamente a las imágenes por su ID
        Map<Long, Image> imageMap = currentImages.stream()
                .collect(Collectors.toMap(Image::getId, image -> image));

        // Limpiar la lista existente sin romper la referencia
        currentImages.clear();

        // Reordenar las imágenes de acuerdo con el nuevo orden especificado
        for (Long imageId : imagesId) {
            Image image = imageMap.get(imageId);
            if (image != null) {
                currentImages.add(image);
            }
        }

        System.out.println("despues de ordenar");
        for (Image image : currentImages) {
            System.out.println(image);
        }
        publication.setImages(currentImages);

        System.out.println("en publication");
        for (Image image : publication.getImages()) {
            System.out.println(image);
        }

        publicationRepo.save(publication);
        PublicationResponse publicationResponse = publicationMapper.toPublicationResponse(publication);
        return ResponseEntity.status(HttpStatus.CREATED).body(publicationResponse);
    }

    @Override
    public void changeDeletedStatus(Long id) {
        Publication publication = findById(id);
        publication.setDeleted(!publication.isDeleted());
        publicationRepo.save(publication);
    }

    @Override
    public Publication addView(Publication publication) {
        publication.setViews(publication.getViews() + 1);
        return publication;
    }

    private Publication findById(Long id) {
        Optional<Publication> publication = publicationRepo.findById(id);
        return publication.orElseThrow(() -> new RuntimeException("Publication not found"));
    }

    private Category getCategory(Long id) {
        return categoryService.findCategoryById(id);
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
