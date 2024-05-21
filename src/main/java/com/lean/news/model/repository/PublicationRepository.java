/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lean.news.model.repository;

import com.lean.news.model.entity.Publication;

import java.util.List;

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

    @Query("SELECT p FROM Publication p WHERE p.deleted = false ORDER BY p.creationDate DESC ")
    public List<Publication> findActivePublications();

    @Query("SELECT p FROM Publication p WHERE p.title LIKE %:word%")
    public List<Publication> findTitleByTitle(@Param("word") String word);

    @Query("SELECT p FROM Publication p WHERE p.author = :author")
    public List<Publication> findByAuthor(@Param("author") String author);

    @Query("SELECT p FROM Publication p WHERE p.creationDate IN (SELECT MAX(p2.creationDate) FROM Publication p2 WHERE p2.deleted = false GROUP BY p2.category)")
    public List<Publication> findLastPublicationByCategory();
////     @Query("SELECT p2 FROM Publication p2 GROUP BY p2.category")
////    public List<Publication> findLastPublicationByCategory();
//     @Query("SELECT p FROM Publication p ORDER BY p.creationDate DESC")
//    public List<Publication> findLastPublicationByCategory();

}
