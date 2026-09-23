package com.lean.news.service;


import com.lean.news.model.entity.Category;
import com.lean.news.model.mapper.CategoryMapper;
import com.lean.news.repository.ICategoryRepository;
import com.lean.news.dto.response.CategoryResponse;
import com.lean.news.service.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    ICategoryRepository ICategoryRepository;

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> findAll() {
        List<Category> listCategoryEntities = ICategoryRepository.findAll();

        return categoryMapper.toListCategoryResponse(listCategoryEntities);

    }


    public Category findCategoryByName(String name) {
        Optional<Category> categoryOptional = ICategoryRepository.findByName(name);

        return ICategoryRepository.findByName(name)
                .orElseThrow(() ->
                        new EntityNotFoundException("No existe la categoría"));

    }
}

