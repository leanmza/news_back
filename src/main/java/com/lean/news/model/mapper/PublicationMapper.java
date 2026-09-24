package com.lean.news.model.mapper;


import com.lean.news.model.entity.Category;
import com.lean.news.model.entity.Publication;
import com.lean.news.dto.request.PublicationRequestDTO;
import com.lean.news.dto.response.PublicationResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PublicationMapper {

    Publication toPublication(PublicationRequestDTO publicationRequestDTO);

    List<PublicationResponse> toListPublicationResponse(List<Publication> publicationList);

    PublicationResponse toPublicationResponse(Publication publication);

    default Category mapCategory(String categoryName) {
        if (categoryName != null) {
            Category category = new Category();
            category.setName(categoryName);
            return category;
        } else {
            return null;
        }
    }
}