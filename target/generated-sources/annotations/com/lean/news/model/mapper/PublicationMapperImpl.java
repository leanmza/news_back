package com.lean.news.model.mapper;

import com.lean.news.model.entity.Image;
import com.lean.news.model.entity.Publication;
import com.lean.news.rest.request.CreatePublicationRequest;
import com.lean.news.rest.response.PublicationResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-03T10:37:25-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class PublicationMapperImpl implements PublicationMapper {

    @Override
    public Publication toPublication(CreatePublicationRequest createPublicationRequest) {
        if ( createPublicationRequest == null ) {
            return null;
        }

        Publication publication = new Publication();

        publication.setTitle( createPublicationRequest.getTitle() );
        publication.setBody( createPublicationRequest.getBody() );
        publication.setCategory( mapCategory( createPublicationRequest.getCategory() ) );
        publication.setSubscriberContent( createPublicationRequest.isSubscriberContent() );

        return publication;
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
        publicationResponse.setId( publication.getId() );
        publicationResponse.setTitle( publication.getTitle() );
        publicationResponse.setBody( publication.getBody() );
        publicationResponse.setCreationDate( publication.getCreationDate() );
        publicationResponse.setAuthor( publication.getAuthor() );
        publicationResponse.setCategory( publication.getCategory() );
        publicationResponse.setSubscriberContent( publication.isSubscriberContent() );
        publicationResponse.setDeleted( publication.isDeleted() );
        publicationResponse.setVisualizations( publication.getVisualizations() );

        return publicationResponse;
    }
}
