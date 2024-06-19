package com.lean.news.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@ToString(exclude = {"publication", "user"})
public class Commentary {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    @Size(max = 140)
    private String commentary;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="USER", nullable = false)
    private User user;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PUBLICATION", nullable = false)
    private Publication publication;

}
