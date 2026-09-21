package com.lean.news.service.interfaces;

import com.lean.news.rest.response.CategoryResponse;

import java.util.List;

public interface ICategoryService {
    List<CategoryResponse> findAll();
}
