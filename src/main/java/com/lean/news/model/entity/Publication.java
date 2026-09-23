/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lean.news.model.entity;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.*;


/**
 * @author Lean
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "publications")
@ToString(exclude = {"images", "author"})
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String title;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    @NotBlank
    private String body;

    @Column(length = 250, nullable = false)
    @NotBlank
    @Size(max = 250)
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
    private UserSec author;

    @ManyToOne
    @JoinColumn(name="CATEGORY", nullable = false)
    private Category category;

    @NotNull
    @Column( nullable = false)
    private boolean deleted;

    @NotNull
    @Column(nullable = false)
    private Long views;

}
