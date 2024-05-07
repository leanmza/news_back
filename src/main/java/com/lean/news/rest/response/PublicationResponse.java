package com.lean.news.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.lean.news.model.entity.Category;
import com.lean.news.model.entity.Image;
import com.lean.news.model.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PublicationResponse {

    private String id;

    private String title;

    private String body;

    private LocalDateTime creationDate;

    private List<Map<String, Object>> images;

    private User author;

    private Category category;

    private boolean subscriberContent;

    private boolean deleted;

    private Integer views;

    public void setImages(List<Image> images) {
        this.images = mapImages(images);
    }

    private List<Map<String, Object>> mapImages(List<Image> images) {
        return images.stream()
                .map(image -> {
                    Map<String, Object> imageMap = new HashMap<>();
                    imageMap.put("id", image.getId());
                    imageMap.put("imageUrl", image.getImageUrl());
                    return imageMap;
                })
                .collect(Collectors.toList());
    }

}
