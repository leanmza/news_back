package com.lean.news.controller;


import com.lean.news.rest.request.CreatePublicationRequest;

import com.lean.news.rest.request.UpdatePublicationRequest;

import com.lean.news.rest.response.ListPublicationResponse;

import com.lean.news.rest.response.PublicationResponse;


import com.lean.news.service.PublicationService;
import com.lean.news.service.interfaces.IPublicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping(path = "api/publication")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody @Valid CreatePublicationRequest createPublicationRequest/*,
      @RequestPart("images") List<MultipartFile> images*/) {
        System.out.println(createPublicationRequest);
        return publicationService.create(createPublicationRequest/*, images*/);
    }

    @PatchMapping(value ="{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PublicationResponse> update(@PathVariable String id, @Valid @RequestBody UpdatePublicationRequest updatePublicationRequest){
        PublicationResponse publicationResponse = publicationService.update(id, updatePublicationRequest);
        return ResponseEntity.ok().body(publicationResponse);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable String id){
        publicationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListPublicationResponse> listAllPublications() {
        return ResponseEntity.ok().body(publicationService.listAllPublications());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    //Devuelve una publicacion por id;
    public ResponseEntity<PublicationResponse> getOnePublication(@PathVariable String id) {
        publicationService.updateView(id);
        return ResponseEntity.ok().body(publicationService.getOnePublicationById(id));
    }
}
