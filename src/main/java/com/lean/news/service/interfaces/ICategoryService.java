package com.lean.news.service.interfaces;

import com.lean.news.dto.response.CategoryResponse;
import com.lean.news.model.entity.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<CategoryResponse> findAllCategories();

    Category findCategoryByName(String name);

    Category findCategoryById(Long id);
}
