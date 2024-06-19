package com.lean.news.service;

import com.lean.news.exception.EntityNotFound;
import com.lean.news.model.entity.Commentary;
import com.lean.news.model.entity.Publication;
import com.lean.news.model.mapper.CommentaryMapper;
import com.lean.news.model.repository.CommentaryRepository;
import com.lean.news.rest.request.CreateCommentaryRequest;
import com.lean.news.rest.response.CommentaryResponse;
import com.lean.news.rest.response.ListCommentaryResponse;
import com.lean.news.rest.response.PublicationResponse;
import com.lean.news.service.interfaces.ICommentaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentaryService implements ICommentaryService {

    @Autowired
    private CommentaryRepository commentaryRepository;

    @Autowired
    private CommentaryMapper commentaryMapper;

    @Autowired
    private PublicationService publicationService;

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<?> create(CreateCommentaryRequest createCommentaryRequest) {

        Commentary commentary = commentaryMapper.toCommentary(createCommentaryRequest);

        commentary.setPublication(publicationService.findById(createCommentaryRequest.getIdPublication()));
        commentary.setUser(userService.getUserLogged());

        publicationService.setCommentary(commentary);
        commentaryRepository.save(commentary);

        CommentaryResponse commentaryResponse = commentaryMapper.toCommentaryResponse(commentary);

        return ResponseEntity.status(HttpStatus.CREATED).body(commentaryResponse);
    }

    @Override
    public void delete(String id) {
        commentaryRepository.deleteById(id);
    }

    @Override
    public ListCommentaryResponse listCommentary() {
        List<Commentary> listCommentaries = commentaryRepository.findAll();


        if (listCommentaries.isEmpty()){
            throw new EntityNotFound("No hay comentarios");
        } else {
            ListCommentaryResponse listCommentaryResponse = new ListCommentaryResponse();
            listCommentaryResponse.setCommentarys(commentaryMapper.toListCommentaryResponse(listCommentaries));

            return listCommentaryResponse;
        }
    }

    @Override
    public ListCommentaryResponse listCommentaryByPublication(Publication idPublication) {
        List<Commentary> listByPublication = commentaryRepository.findByPublication(idPublication);

        if(listByPublication.isEmpty()){
            throw new EntityNotFound("No hay comentarios");
        } else {
            ListCommentaryResponse listCommentaryResponse = new ListCommentaryResponse();
            listCommentaryResponse.setCommentarys(commentaryMapper.toListCommentaryResponse(listByPublication));
            return listCommentaryResponse;
        }
    }


}
