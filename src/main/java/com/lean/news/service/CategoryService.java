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
    ICategoryRepository categoryRepo;

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> findAllCategories() {
        List<Category> listCategoryEntities = categoryRepo.findAll();

        return categoryMapper.toListCategoryResponse(listCategoryEntities);

    }

    @Override
    public Category findCategoryByName(String name) {
        Optional<Category> categoryOptional = categoryRepo.findByName(name);

        return categoryRepo.findByName(name)
                .orElseThrow(() ->
                        new EntityNotFoundException("No existe la categoría"));

    }

    @Override
    public Category findCategoryById(Long id) {
       Category category = categoryRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Category not found"));
        return category;
    }
}

