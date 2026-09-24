package com.lean.news.controller;


import com.lean.news.dto.request.PublicationRequestDTO;

import com.lean.news.dto.response.ListPublicationResponse;

import com.lean.news.dto.response.PublicationResponse;


import com.lean.news.service.interfaces.IPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.*;


@RestController
@RequestMapping(path = "/api/publications")
@PreAuthorize("denyAll()")
public class PublicationController {

    @Autowired
    private IPublicationService publicationService;

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List> getAllActivePublications() {
        List<PublicationResponse> publications = publicationService.findAllActivePublications();
        return ResponseEntity.ok(publications);
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    //Devuelve una publicacion por id;
    public ResponseEntity<PublicationResponse> getPublicationById(@PathVariable Long id) {
        PublicationResponse publication = publicationService.findPublicationById(id);
        return ResponseEntity.ok(publication);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createPublication(@RequestPart(value = "publication") @Valid PublicationRequestDTO publicationRequestDTO,
                                            @RequestPart(value = "images", required = false) List<MultipartFile> images) {
        PublicationResponse newPublication = publicationService.save(publicationRequestDTO,images);
        return ResponseEntity.ok(newPublication);
    }

    //Separo la edicion de controladores en tres por problemas si alguna part es nula
    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN')")
    //Edita un publicacion cuando se envian datos e imagenes
    public PublicationResponse updatePublication(@PathVariable Long id, @RequestPart(value = "publication", required = false) PublicationRequestDTO publicationRequestDTO,
                                               @RequestPart(value = "images", required = false) List<MultipartFile> images) {

        PublicationResponse updatedPublication = publicationService.update(id, publicationRequestDTO,images);
        return publicationService.update(id, publicationRequestDTO, images);
    }

    @PatchMapping("/images/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    //Edita la posición de las imagenes en una publicación.
    public ResponseEntity<?> updateNewPositions(@PathVariable Long id,
                                                @RequestBody(required = false) List<Long> idList) {
        System.out.println(idList);

        return publicationService.arrangeImages(id, idList);

    }

    @DeleteMapping(value = "/images/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    //Elimina una imagen de una publicación
    public ResponseEntity deleteImage(@PathVariable Long id, @RequestBody Map<String, String> body) {
        Long imageId = Long.valueOf(body.get("imageId"));
        publicationService.deleteImage(imageId);
        return ResponseEntity.ok("Image successfully deleted");
    }

    @PatchMapping("/status/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity changeDeletedStatus(@PathVariable Long id) {
        publicationService.changeDeletedStatus(id);
        return ResponseEntity.ok("Status successfully changed");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id) {
        publicationService.delete(id);
        return ResponseEntity.ok("Publication successfully deleted");
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List> listAllPublications() {
        List<PublicationResponse> publications = publicationService.findAllPublications();
        return ResponseEntity.ok(publications);
    }

    @GetMapping("/last")
    @PreAuthorize("permitAll()")
    //devuelve lista de la última publicación de cada categoría
    public ResponseEntity<List> listLastPublications() {
        List<PublicationResponse> lastPublications = publicationService.findLastPublications();
        return ResponseEntity.ok(lastPublications);
    }



}
