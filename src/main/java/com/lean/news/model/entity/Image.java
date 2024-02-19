package com.lean.news.model.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Lean
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(nullable = false)
    private String name;


    private String mime;  // IDENTIFICA EL FORMATO DE LA IMAGEN

    @Lob
    @Basic(fetch = FetchType.LAZY) // ALMACENA LOS DATOS BINARIOS DE LA IMAGEN
    private byte[] content;

}
