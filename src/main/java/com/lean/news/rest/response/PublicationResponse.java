package com.lean.news.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.lean.news.model.entity.Category;
import com.lean.news.model.entity.Image;
import com.lean.news.model.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PublicationResponse {

    private String id;

    private String title;

    private String body;

    private LocalDateTime creationDate;

//    private Image image;

    private User author;

    private Category category;

    private boolean subscriberContent;

    private boolean deleted;

    private Integer visualizations;

}
