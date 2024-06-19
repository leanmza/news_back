package com.lean.news.model.repository;

import com.lean.news.model.entity.Commentary;
import com.lean.news.model.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentaryRepository extends JpaRepository<Commentary, String> {

    @Query ("SELECT c FROM Commentary c WHERE c.publication = :idPublication")
    public List<Commentary> findByPublication(@Param("idPublication") Publication idPublication);

}
