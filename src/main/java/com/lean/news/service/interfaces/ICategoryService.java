package com.lean.news.service.interfaces;

import com.lean.news.dto.response.CategoryResponse;

import java.util.List;

public interface ICategoryService {
    List<CategoryResponse> findAll();
}
