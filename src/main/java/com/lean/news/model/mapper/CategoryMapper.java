package com.lean.news.model.mapper;


import com.lean.news.model.entity.Category;
import com.lean.news.rest.response.CategoryResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    List<CategoryResponse> toListCategoryResponse(List<Category> category);
}
