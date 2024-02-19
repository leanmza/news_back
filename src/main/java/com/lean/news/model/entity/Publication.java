/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lean.news.model.entity;

import com.lean.news.enums.Category;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
public class Publication {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(nullable = false)
    private String title;
    
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String body;

    @Column(nullable = false)
    private LocalDateTime dateLog;
    
    @OneToOne
    private Image image;
    
    @OneToOne
    @Column(nullable = false)
    private User writer;

    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private boolean subscriberContent = false;

}
