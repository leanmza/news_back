package com.lean.news.controller;


import com.lean.news.rest.request.CreatePublicationRequest;

import com.lean.news.rest.request.UpdatePublicationRequest;

import com.lean.news.rest.response.ListPublicationResponse;

import com.lean.news.rest.response.PublicationResponse;


import com.lean.news.service.PublicationService;
import com.lean.news.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.*;


@Controller
@RequestMapping(path = "api/publication")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @PostMapping(path = "/create",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestPart(value = "publication") @Valid CreatePublicationRequest createPublicationRequest,
                                    @RequestPart(value = "images", required = false) List<MultipartFile> images) {

        return publicationService.create(createPublicationRequest, images);
    }

    //Separo la edicion de controladores en tres por problemas si alguna part es nula
    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    //Edita un publicacion cuando se envian datos e imagenes
    public ResponseEntity<?> update(@PathVariable String id, @RequestPart(value = "publication", required = false) UpdatePublicationRequest updatePublicationRequest,
                                    @RequestPart(value = "images", required = false) List<MultipartFile> images,
                                    @RequestPart(value = "idImages", required = false) List<String> idImages) {
        return publicationService.update(id, updatePublicationRequest, images, idImages);
    }

    @DeleteMapping(value = "/images/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    //Elimina una imagen de una publicación
    public ResponseEntity<Void> deleteImage(@PathVariable String id, @RequestBody Map<String, String> body) {
        String imageId = body.get("imageId");
        publicationService.deleteImage(imageId);
        return ResponseEntity.noContent().build();
    }


    @PatchMapping(value = "/status/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> changeDeletedStatus(@PathVariable String id) {
        publicationService.changeDeletedStatus(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        publicationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListPublicationResponse> listAllPublications() {
        return ResponseEntity.ok().body(publicationService.listAllPublications());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListPublicationResponse> listActivePublications() {
        return ResponseEntity.ok().body(publicationService.listActivePublications());
    }

    @GetMapping(path = "/last", produces = MediaType.APPLICATION_JSON_VALUE)
    //devuelve lista de la última publicación de cada categoría
    public ResponseEntity<ListPublicationResponse> listLastPublications() {
        return ResponseEntity.ok().body(publicationService.listLastPublications());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    //Devuelve una publicacion por id;
    public ResponseEntity<PublicationResponse> getOnePublication(@PathVariable String id) {

        publicationService.updateView(id);

        return ResponseEntity.ok().body(publicationService.getOnePublicationById(id));
    }

}
