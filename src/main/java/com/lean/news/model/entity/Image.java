package com.lean.news.model.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Lean
 */
@Entity
@Data
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

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
