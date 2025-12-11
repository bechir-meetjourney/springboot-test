package com.nefziraed.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nefziraed.enums.Genre;
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
public class Artiste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nom;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private Double totalRoyalties;

    @OneToMany(mappedBy = "artiste", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Album> albums = new ArrayList<>();
}
