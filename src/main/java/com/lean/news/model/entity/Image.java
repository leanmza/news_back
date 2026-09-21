package com.lean.news.model.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;


/**
 *
 * @author Lean
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "images")
@ToString(exclude = "publication")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "URL", nullable = false)
    private String imageUrl;

    @Column(name = "ID_CLOUDINARY", nullable = false)
    private String cloudinaryId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PUBLICATION_ID", nullable = false)
    private Publication publication;

    public Image(String name, String imageUrl, String cloudinaryId) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.cloudinaryId = cloudinaryId;
    }

}
