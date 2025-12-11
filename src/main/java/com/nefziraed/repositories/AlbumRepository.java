package com.nefziraed.repositories;

import com.nefziraed.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    Optional<Album> findByTitre(String titre);

    @Query("SELECT a FROM Album a WHERE a.nombreEcoutes >= :minEcoutes")
    List<Album> findPopularAlbums(@Param("minEcoutes") Integer minEcoutes);
}
