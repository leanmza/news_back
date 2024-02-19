package com.lean.news.model.mapper;


import com.lean.news.model.entity.Publication;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PublicationMapper {

    Publication toPublication(CreateNewsRequest createNewsRequest);

    List<PublicationResponse> toListPublicationResponse(List<Publication> publicationList);

    PublicationResponse toPublicationResponse(Publication publication);
}
