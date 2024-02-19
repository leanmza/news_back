/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lean.news.model.repository;

import com.lean.news.model.entity.Publication;

import com.lean.news.enums.Category;
import java.util.List;

import com.lean.news.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lean
 */
@Repository
public interface PublicationRepository extends JpaRepository<Publication, String> {

    @Query("SELECT ne FROM News ne WHERE ne.title LIKE %:word%")
    public List<Publication> findTitleByWord(@Param("word") String word);

    @Query("SELECT ne FROM News ne ORDER BY ne.dateLog DESC")
    public List<Publication> listOrderedNews();

    @Query("SELECT  ne FROM News ne WHERE ne.category = :category ORDER BY ne.dateLog DESC")
    public List<Publication> listNewsByCategory(@Param("category") Category categoryEnum);

    @Query("SELECT ne FROM News ne WHERE ne.writer = :writer")
    public List<Publication> listNewsByWriter(@Param("writer") User writer);

//      QUERYS PARA ORDENAR TABLA DE ADMINISTRAR NEWS
    @Query("SELECT ne FROM News ne ORDER BY ne.title DESC")
    public List<Publication> orderByTitleDesc(); //ordena por título Z-A

    @Query("SELECT ne FROM News ne ORDER BY ne.title ASC")
    public List<Publication> orderByTitleAsc(); //ordena por título A-Z

    @Query("SELECT ne FROM News ne ORDER BY ne.writer DESC")
    public List<Publication> orderByWriterDesc(); //ordena por writer Z-A

    @Query("SELECT ne FROM News ne ORDER BY ne.writer ASC")
    public List<Publication> orderByWriterAsc(); //ordena por writer A-Z

    @Query("SELECT ne FROM News ne ORDER BY ne.dateLog ASC")
    public List<Publication> listOrderedNewsAsc();

}
