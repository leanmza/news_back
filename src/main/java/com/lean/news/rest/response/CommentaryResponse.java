package com.lean.news.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lean.news.model.entity.Publication;
import com.lean.news.model.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentaryResponse {

    private String id;

    private String commentary;

    private String user;

    private String publication;

    public void setUser(User user) {
        if (user != null) {
            this.user = user.getName();
        }
    }

    public void setPublication(Publication publication){
        if (publication != null) {
            this.publication = publication.getId();
        }
    }

}
