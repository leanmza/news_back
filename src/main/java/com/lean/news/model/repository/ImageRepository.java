
package com.lean.news.model.repository;

import com.lean.news.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, String>{
    List<Image> findByOrderById();

}
