package com.nefziraed.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titre;

    private Double prix;

    private Integer nombreEcoutes;

    @ManyToOne
    @JoinColumn(name = "artiste_id")
    @JsonBackReference
    private Artiste artiste;

    @ManyToMany(mappedBy = "albums")
    @JsonBackReference(value = "utilisateur-album")
    private List<Utilisateur> utilisateurs = new ArrayList<>();

    @Transient
    private String artisteNom;
}
