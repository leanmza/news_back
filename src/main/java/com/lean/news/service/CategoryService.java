package com.lean.news.service;


import com.lean.news.model.entity.Category;
import com.lean.news.model.mapper.CategoryMapper;
import com.lean.news.model.repository.CategoryRepository;
import com.lean.news.rest.response.ListCategoriesResponse;
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
    public ListCategoriesResponse listAllCategories() {
        List<Category> listCategoryEntities = categoryRepository.findAll();
        if (listCategoryEntities.isEmpty()) {
            throw new EntityNotFoundException("No hay categorías cargadas");
        } else {
            ListCategoriesResponse listCategoriesResponse = new ListCategoriesResponse();
            listCategoriesResponse.setCategories(categoryMapper.toListCategoryResponse(listCategoryEntities));
            return listCategoriesResponse;
        }
    }


    public Category findCategoryByName(String name) {
        Optional<Category> categoryOptional = categoryRepository.findByName(name);

        if (categoryOptional.isEmpty()) {
            throw new EntityNotFoundException("No existe la categoría");
        } else {
            return categoryOptional.get();
        }

    }
}

