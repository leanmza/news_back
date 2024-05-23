/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lean.news.model.entity;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;


/**
 * @author Lean
 */
@Entity
@Data
@NoArgsConstructor
@ToString(exclude = {"images", "author"})
public class Publication {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String body;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    @Size(max = 140)
    private String header;

    @Column(name = "CREATION_DATE", nullable = false)
    private LocalDateTime creationDate;

    @JsonIgnore
    @JsonManagedReference
    @Column(name = "IMAGES")
    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="USER_CREATOR", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name="CATEGORY", nullable = false)
    private Category category;

    @Column(nullable = false)
    private boolean subscriberContent;

    @NotNull
    @Column( nullable = false)
    private boolean deleted;

    @NotNull
    @Column(nullable = false)
    private Integer views;

}
