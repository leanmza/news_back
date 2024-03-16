package com.lean.news.controller;

import com.lean.news.rest.request.CreatePublicationRequest;
import com.lean.news.rest.request.CreateUserRequest;
import com.lean.news.rest.request.UpdatePublicationRequest;
import com.lean.news.rest.request.UpdateUserRequest;
import com.lean.news.rest.response.ListPublicationResponse;
import com.lean.news.rest.response.ListUsersResponse;
import com.lean.news.rest.response.PublicationResponse;

import com.lean.news.rest.response.UserResponse;
import com.lean.news.service.interfaces.IPublicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "api/publications")
public class PublicationController {

    @Autowired
    private IPublicationService publicationService;

    @PostMapping(path = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PublicationResponse> create(/*@RequestPart("publication")*/ @Valid @RequestBody CreatePublicationRequest createPublicationRequest) {
        PublicationResponse publicationResponse = publicationService.create(createPublicationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(publicationResponse);
    }

    @PatchMapping(value ="{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
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
}
