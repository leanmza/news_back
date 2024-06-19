package com.lean.news.controller;

import com.lean.news.model.entity.Publication;
import com.lean.news.rest.request.CreateCommentaryRequest;
import com.lean.news.rest.response.ListCommentaryResponse;
import com.lean.news.service.CommentaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@Controller
@RequestMapping(path = "/api/comment")
public class CommentaryController {

    @Autowired
    private CommentaryService commentaryService;

    @PostMapping(path = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody @Valid CreateCommentaryRequest createCommentaryRequest) {
        System.out.println(createCommentaryRequest);
        return commentaryService.create(createCommentaryRequest);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable String id){
        commentaryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListCommentaryResponse> listCommentarys(){
        return ResponseEntity.ok().body(commentaryService.listCommentary());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<ListCommentaryResponse> listCommentarysByPublication(@PathVariable Publication id){
        return ResponseEntity.ok().body((commentaryService.listCommentaryByPublication(id)));
    }
}
