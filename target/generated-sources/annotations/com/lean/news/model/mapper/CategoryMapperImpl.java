package com.lean.news.model.mapper;

import com.lean.news.model.entity.Category;
import com.lean.news.rest.response.CategoryResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-16T10:51:37-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public List<CategoryResponse> toListCategoryResponse(List<Category> category) {
        if ( category == null ) {
            return null;
        }

        List<CategoryResponse> list = new ArrayList<CategoryResponse>( category.size() );
        for ( Category category1 : category ) {
            list.add( categoryToCategoryResponse( category1 ) );
        }

        return list;
    }

    protected CategoryResponse categoryToCategoryResponse(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setId( category.getId() );
        categoryResponse.setName( category.getName() );

        return categoryResponse;
    }
}
