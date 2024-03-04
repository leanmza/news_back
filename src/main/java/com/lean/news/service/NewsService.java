/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lean.news.service;

import com.lean.news.model.entity.Image;
import com.lean.news.model.entity.Publication;

import com.lean.news.enums.Category;
import com.lean.news.exception.MyException;
import com.lean.news.model.repository.PublicationRepository;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Lean
 */
@Service
public class NewsService {

   /* @Autowired
    private PublicationRepository newsRepository;

    @Autowired
    private ImageService imageService;

    @Transactional
    public void createNews(String title, String body, MultipartFile imageFile, String writerEmail, String category, boolean subscriberContent)
            throws MyException {

        validate(title, body, writerEmail, category);

        Publication publication = new Publication();

        publication.setTitle(title);
        publication.setBody(body);
        publication.setDateLog(LocalDateTime.now());

        if (imageFile != null) {

            Image image = imageService.saveImage(imageFile);
            publication.setImage(image);
        }

        Writer writer = writerRepository.findWriterByEmail(writerEmail);

        publication.setWriter(writer);

        publication.setCategory(Category.valueOf(category));

        if (subscriberContent == true) {

            publication.setSubscriberContent(true);
        }

        newsRepository.save(publication);
    }

    @Transactional
    public void actualizeNews(String id, String title, String body, MultipartFile imageFile, String writerEmail, String category, boolean subscriberContent)
            throws MyException {

        validate(title, body, writerEmail, category);

        Optional<Publication> optionalNews = newsRepository.findById(id);

        if (optionalNews.isPresent()) {

            Publication publication = optionalNews.get();

            publication.setTitle(title);
            publication.setBody(body);

            if (!(imageFile.isEmpty())) { ///Comprueba si el imageFile no está vacio 

                String idImage = publication.getImage().getId(); // idImage toma el valor del id de la imagen existente

                Image image = imageService.actualizeImage(idImage, imageFile); // Actualiza la imagen en su repo con el id existente y el nuevo archivo

                publication.setImage(image); //Establece la imagen nueva

            }

            publication.setCategory(Category.valueOf(category));

            if (subscriberContent == false) {

                publication.setSubscriberContent(false);
            } else if (subscriberContent == true) {

                publication.setSubscriberContent(true);
            }

            newsRepository.save(publication);
        }
    }
*/
  /*  private void validate(String title, String body, String writerEmail, String category) throws MyException {
        if (title.isEmpty() || title == null) {
            throw new MyException("El título no puede estar vacío o ser nulo");
        }

        if (title.isEmpty() || body == null) {
            throw new MyException("El cuerpo de la noticia no puede estar vacío o ser nulo");
        }

        if (writerEmail.isEmpty() || writerEmail == null) {
            throw new MyException("El id del autor no puede estar vacío o ser nulo");
        }

        if (checkCategory(category) == false) {
            throw new MyException("La categoría ingresada no es válida");
        }

    }*/

/*    private boolean checkCategory(String category) {

        boolean check = false;

        Category[] listCategorys = Category.values();

        for (int i = 0; i < listCategorys.length; i++) {

            if (category.equals(listCategorys[i].toString())) {
                check = true;

                break;

            }
        }
        return check;
    }*/

/*    @Transactional
    public void deleteNews(String id) throws MyException {

        System.out.println("id News " + id);
        Optional<Publication> optionalNews = newsRepository.findById(id);
        if (optionalNews.isPresent()) {
            Publication publication = optionalNews.get();
            newsRepository.delete(publication);
        }
    }*/

/*    @Transactional(readOnly = true)
    public List<Publication> newsList() { //Muestra la noticia más nueva primero
        List<Publication> publicationList = new ArrayList();

        publicationList = newsRepository.listOrderedNews();

        return publicationList;

    }*/

/*    @Transactional
    public Publication getOne(String id) {
        return newsRepository.getOne(id);
    }*/

/*    @Transactional(readOnly = true)
    public List<Publication> findNewsByTitle(String word) { //Muestra las noticias con la palabra buscada
        List<Publication> publicationList = new ArrayList();
        publicationList = newsRepository.findTitleByWord(word);
        return publicationList;
    }

    @Transactional(readOnly = true)
    public List<Publication> categoryList(String category) { //Muestra muestra las noticias de la category con la noticia más nueva primero

        List<Publication> publicationList = new ArrayList();

        Category categoryEnum = Category.valueOf(category.toUpperCase());// Category viene en minúsculas del HTML, lo paso a mayúsculas 

        publicationList = newsRepository.listNewsByCategory(categoryEnum);

        return publicationList;
    }*/
/*
    @Transactional(readOnly = true)
    public List<Publication> findNewsByWriter(String id) { //Muestra las noticias con la palabra buscada
        Writer writer = writerRepository.findById(id).get();

        List<Publication> publicationList = new ArrayList();
        publicationList = newsRepository.listNewsByWriter(writer);
        return publicationList;
    }*/

/*    @Transactional(readOnly = true)
    public List<Publication> newsListAsc() { //Muestra la noticia más nueva primero
        List<Publication> publicationList = new ArrayList();

        publicationList = newsRepository.listOrderedNewsAsc();

        return publicationList;
    }*/

/*    @Transactional(readOnly = true)
    public List<Publication> newsListWriterAZ() { //Muestra la noticia más nueva primero
        List<Publication> publicationList = new ArrayList();

        publicationList = newsRepository.orderByWriterDesc();

        return publicationList;
    }

    @Transactional(readOnly = true)
    public List<Publication> newsListWriterZA() { //Muestra la noticia más nueva primero
        List<Publication> publicationList = new ArrayList();

        publicationList = newsRepository.orderByWriterAsc();

        return publicationList;
    }

    @Transactional(readOnly = true)
    public List<Publication> newsListTitleDesc() { //Muestra la noticia más nueva primero
        List<Publication> publicationList = new ArrayList();

        publicationList = newsRepository.orderByTitleDesc();

        return publicationList;
    }

    @Transactional(readOnly = true)
    public List<Publication> newsListTitleAsc() { //Muestra la noticia más nueva primero
        List<Publication> publicationList = new ArrayList();

        publicationList = newsRepository.orderByTitleAsc();

        return publicationList;
    }*/
}
