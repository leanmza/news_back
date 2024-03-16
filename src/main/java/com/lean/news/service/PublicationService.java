package com.lean.news.service;

import com.lean.news.enums.Category;
import com.lean.news.exception.EntityNotFound;
import com.lean.news.exception.UserNotFound;
import com.lean.news.model.entity.Publication;
import com.lean.news.model.entity.User;
import com.lean.news.model.mapper.PublicationMapper;
import com.lean.news.model.repository.PublicationRepository;
import com.lean.news.rest.request.CreatePublicationRequest;
import com.lean.news.rest.request.UpdatePublicationRequest;
import com.lean.news.rest.response.ListPublicationResponse;
import com.lean.news.rest.response.PublicationResponse;
import com.lean.news.service.interfaces.IPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PublicationService implements IPublicationService {

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private PublicationMapper publicationMapper;

    @Autowired
    private UserService userService;


    @Override
    public PublicationResponse create(CreatePublicationRequest createPublicationRequest) {

        Publication publication = publicationMapper.toPublication(createPublicationRequest);

        publication.setVisualizations(0);
        publication.setDeleted(false);
        publication.setCreationDate(LocalDateTime.now());
       //publication.setAuthor(getUserLogged());
        publicationRepository.save(publication);


        return publicationMapper.toPublicationResponse(publication);
    }


    @Override
    public void delete(String id) {
        Publication publication = findById(id);
        publication.setDeleted(true);
        publicationRepository.save(publication);
    }

    @Override
    public ListPublicationResponse listAllPublications() {
        List<Publication> listPublications = publicationRepository.findAll();
        if (listPublications.isEmpty()) {
            throw new EntityNotFound("No hay publicaciones");
        } else {
            ListPublicationResponse listPublicationResponse = new ListPublicationResponse();
            listPublicationResponse.setPublications(publicationMapper.toListPublicationResponse(listPublications));
            return listPublicationResponse;
        }
    }

    @Override
    public PublicationResponse update(String id, UpdatePublicationRequest updatePublicationRequest) {
        Publication publication = findById(id);
        Publication publicationUpdate = updateValues(updatePublicationRequest, publication);
        publicationRepository.save(publicationUpdate);
        return publicationMapper.toPublicationResponse(publicationUpdate);
    }


    @Override
    public ListPublicationResponse findByTitle(String title) {
        List<Publication> listPublications = publicationRepository.findTitleByWord(title);
        if (listPublications.isEmpty()) {
            throw new EntityNotFound("No hay publicaciones con ese título");
        } else {
            ListPublicationResponse listPublicationResponse = new ListPublicationResponse();
            listPublicationResponse.setPublications(publicationMapper.toListPublicationResponse(listPublications));
            return listPublicationResponse;
        }
    }



    @Override
    public ListPublicationResponse findByAuthor(String author) {
        List<Publication> listPublications = publicationRepository.findByAuthor(author);
        if (listPublications.isEmpty()) {
            throw new EntityNotFound("No hay publicaciones con ese autor");
        } else {
            ListPublicationResponse listPublicationResponse = new ListPublicationResponse();
            listPublicationResponse.setPublications(publicationMapper.toListPublicationResponse(listPublications));
            return listPublicationResponse;
        }

    }

//    @Override
//    public ListPublicationResponse findByCategory(String category) {
//        List<Publication> listPublications = publicationRepository.findByCategory(Category.valueOf(category));
//        if (listPublications.isEmpty()) {
//            throw new EntityNotFound("No hay publicaciones con ese autor");
//        } else {
//            ListPublicationResponse listPublicationResponse = new ListPublicationResponse();
//            listPublicationResponse.setPublications(publicationMapper.toListPublicationResponse(listPublications));
//            return listPublicationResponse;
//        }
//    }

    private User getUserLogged() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailLogged = authentication.getName();
        System.out.println("AUTHENTICATION " + authentication);
        System.out.println("emaillogged " + emailLogged);

        Optional<User> optionalUser = userService.findByEmail(emailLogged);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UserNotFound("Usuario no encontrado");
        }
    }

    private Publication findById(String id) {
        Optional<Publication> optionalPublication = publicationRepository.findById(id);
        if (optionalPublication.isEmpty()) {
            throw new EntityNotFound("La publicación no existe");
        } else {
            return optionalPublication.get();
        }
    }


    private Publication updateValues(UpdatePublicationRequest updatePublicationRequest, Publication publication) {
        String title = updatePublicationRequest.getTitle();
        if (title != null) {
            publication.setTitle(title);
        }

        String body = updatePublicationRequest.getBody();
        if (body != null) {
            publication.setBody(body);
        }

        String category = updatePublicationRequest.getCategory();
        if (category != null) {
            publication.setCategory(Category.valueOf(category));
        }

        boolean subscriberContent = updatePublicationRequest.isSubscriberContent();
        if (publication.isSubscriberContent() == false && subscriberContent == true) {
            publication.setSubscriberContent(true);
        } else if (publication.isSubscriberContent() == true && subscriberContent == false) {
            publication.setSubscriberContent(false);
        }
        return publication;
    }
}
