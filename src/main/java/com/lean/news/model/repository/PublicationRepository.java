/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lean.news.model.repository;

import com.lean.news.model.entity.Publication;

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

    @Query("SELECT p FROM Publication p WHERE p.title LIKE %:word%")
    public List<Publication> findTitleByWord(@Param("word") String word);

    @Query("SELECT p FROM Publication p WHERE p.author = :author")
    public List<Publication> findByAuthor(@Param("author") String author);

/*    @Query("SELECT ne FROM News ne ORDER BY ne.dateLog DESC")
    public List<Publication> listOrderedNews();*/

//    @Query("SELECT  ne FROM News ne WHERE ne.category = :category ORDER BY ne.dateLog DESC")
//    public List<Publication> findByCategory(@Param("category") Category category);


}
