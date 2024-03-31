package com.lean.news.model.repository;


import com.lean.news.model.entity.Category;
import com.lean.news.model.entity.Publication;
import com.lean.news.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    @Query("SELECT c FROM Category c WHERE c.name = :name")
    public Optional<Category> findByName(@Param("name")String name);
    ;
}

