package com.lean.news.controller;

import com.lean.news.rest.response.ListCategoriesResponse;

import com.lean.news.service.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path ="api/categories")
public class CategoryController {

    @Autowired
    ICategoryService categoryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListCategoriesResponse> listCategories() {
   
        return ResponseEntity.ok().body(categoryService.listAllCategories());
    }
}
