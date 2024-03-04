package com.lean.news.model.mapper;


import com.lean.news.model.entity.Publication;
import com.lean.news.rest.request.CreatePublicationRequest;
import com.lean.news.rest.response.PublicationResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PublicationMapper {

    Publication toPublication(CreatePublicationRequest createPublicationRequest);

    List<PublicationResponse> toListPublicationResponse(List<Publication> publicationList);

    PublicationResponse toPublicationResponse(Publication publication);
}
