package com.lean.news.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.lean.news.model.entity.Category;
import com.lean.news.model.entity.Image;
import com.lean.news.model.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PublicationResponse {

    private String id;

    private String title;

    private String body;

    private LocalDateTime creationDate;

    private List<String> images;

    private User author;

    private Category category;

    private boolean subscriberContent;

    private boolean deleted;

    private Integer visualizations;

    public void setImages(List<Image> images) {
        this.images = mapImages(images);
    }

    private List<String> mapImages(List<Image> images) {
        return images.stream()
                .map(Image::getImageUrl)
                .collect(Collectors.toList());
    }

}
