package com.lean.news.controller;


import com.lean.news.rest.request.CreatePublicationRequest;

import com.lean.news.rest.request.UpdatePublicationRequest;

import com.lean.news.rest.response.ListPublicationResponse;

import com.lean.news.rest.response.PublicationResponse;


import com.lean.news.service.PublicationService;
import com.lean.news.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "api/publication")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

     @Autowired
     private UserService userService;

     @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping(path = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestPart(value = "publication") @Valid CreatePublicationRequest createPublicationRequest,
                                    @RequestPart(value = "images", required = false) List<MultipartFile> images) {

        return publicationService.create(createPublicationRequest, images);
    }

    @PatchMapping(value = "{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestPart(value = "publication") UpdatePublicationRequest updatePublicationRequest/*,
                                    @RequestPart(value = "images", required = false) List<MultipartFile> images*/) {

        return publicationService.update(id, updatePublicationRequest/*, images*/);
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

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    //Devuelve una publicacion por id;
    public ResponseEntity<PublicationResponse> getOnePublication(@PathVariable String id) {

            publicationService.updateView(id);

        return ResponseEntity.ok().body(publicationService.getOnePublicationById(id));
    }

    /*    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    //Devuelve una publicacion por id;
    FIXME ESTO ANDA PERO ES DESPROLIJO Y COMPLICADO

    public ResponseEntity<PublicationResponse> getOnePublication(@PathVariable String id) {

        Set<String> permittedRoles = new HashSet<>(Arrays.asList("ROLE_READER", "ROLE_ANONYMOUS"));

        if (permittedRoles.contains(userService.getRolLogged())) {
            publicationService.updateView(id);
        }

        return ResponseEntity.ok().body(publicationService.getOnePublicationById(id));
    }*/
}
