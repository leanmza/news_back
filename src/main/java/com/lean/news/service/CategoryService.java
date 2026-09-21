package com.lean.news.service;


import com.lean.news.model.entity.Category;
import com.lean.news.model.mapper.CategoryMapper;
import com.lean.news.model.repository.CategoryRepository;
import com.lean.news.rest.response.CategoryResponse;
import com.lean.news.service.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> findAll() {
        List<Category> listCategoryEntities = categoryRepository.findAll();

        return categoryMapper.toListCategoryResponse(listCategoryEntities);

    }


    public Category findCategoryByName(String name) {
        Optional<Category> categoryOptional = categoryRepository.findByName(name);

        return categoryRepository.findByName(name)
                .orElseThrow(() ->
                        new EntityNotFoundException("No existe la categoría"));

    }
}

