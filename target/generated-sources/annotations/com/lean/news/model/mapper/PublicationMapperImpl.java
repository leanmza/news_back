package com.lean.news.model.mapper;

import com.lean.news.dto.request.PublicationRequestDTO;
import com.lean.news.dto.response.PublicationResponse;
import com.lean.news.model.entity.Image;
import com.lean.news.model.entity.Publication;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-24T18:11:24-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.20 (Microsoft)"
)
@Component
public class PublicationMapperImpl implements PublicationMapper {

    @Override
    public Publication toPublication(PublicationRequestDTO publicationRequestDTO) {
        if ( publicationRequestDTO == null ) {
            return null;
        }

        Publication.PublicationBuilder publication = Publication.builder();

        publication.title( publicationRequestDTO.getTitle() );
        publication.body( publicationRequestDTO.getBody() );
        publication.header( publicationRequestDTO.getHeader() );
        if ( publicationRequestDTO.getCategory() != null ) {
            publication.category( mapCategory( String.valueOf( publicationRequestDTO.getCategory() ) ) );
        }

        return publication.build();
    }

    @Override
    public List<PublicationResponse> toListPublicationResponse(List<Publication> publicationList) {
        if ( publicationList == null ) {
            return null;
        }

        List<PublicationResponse> list = new ArrayList<PublicationResponse>( publicationList.size() );
        for ( Publication publication : publicationList ) {
            list.add( toPublicationResponse( publication ) );
        }

        return list;
    }

    @Override
    public PublicationResponse toPublicationResponse(Publication publication) {
        if ( publication == null ) {
            return null;
        }

        PublicationResponse publicationResponse = new PublicationResponse();

        List<Image> list = publication.getImages();
        if ( list != null ) {
            publicationResponse.setImages( new ArrayList<Image>( list ) );
        }
        publicationResponse.setCategory( publication.getCategory() );
        publicationResponse.setAuthor( publication.getAuthor() );
        publicationResponse.setId( publication.getId() );
        publicationResponse.setTitle( publication.getTitle() );
        publicationResponse.setBody( publication.getBody() );
        publicationResponse.setHeader( publication.getHeader() );
        publicationResponse.setCreationDate( publication.getCreationDate() );
        publicationResponse.setDeleted( publication.isDeleted() );
        if ( publication.getViews() != null ) {
            publicationResponse.setViews( publication.getViews().intValue() );
        }

        return publicationResponse;
    }
}
