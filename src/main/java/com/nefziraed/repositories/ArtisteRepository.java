package com.nefziraed.repositories;

import com.nefziraed.entities.Artiste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtisteRepository extends JpaRepository<Artiste, Long> {

    Optional<Artiste> findByNom(String nom);

    @Query("SELECT a.nom FROM Artiste a ORDER BY a.totalRoyalties DESC")
    List<String> findAllArtistesOrderByRoyaltiesDesc();
}
