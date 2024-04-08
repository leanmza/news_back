package com.lean.news.controller;


import com.lean.news.rest.request.CreatePublicationRequest;

import com.lean.news.rest.request.UpdatePublicationRequest;

import com.lean.news.rest.response.ListPublicationResponse;

import com.lean.news.rest.response.PublicationResponse;


import com.lean.news.service.PublicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "api/publication")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;


/*    @PostMapping(path = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(HttpServletRequest request) throws ServletException, IOException {

        Collection<Part> parts = request.getParts();

        for (Part part : parts) {
            String name = part.getName();
            System.out.println(name);


                String value = request.getParameter(name);
                // Aquí puedes acceder al valor del campo de formulario
                System.out.println(value);

        }


        return null;
    }*/
    @PostMapping(path = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestPart(value = "publication") @Valid CreatePublicationRequest createPublicationRequest,
                                    @RequestPart(value = "images", required = false)  List<MultipartFile> images) {
            System.out.println(createPublicationRequest);
        return publicationService.create(createPublicationRequest, images);
    }
    @PatchMapping(value ="{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody UpdatePublicationRequest updatePublicationRequest,
                                                      @RequestPart(value = "images", required = false) List<MultipartFile> images){

        return publicationService.update(id,updatePublicationRequest,images);
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
