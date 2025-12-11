package com.nefziraed.services;

import com.nefziraed.entities.Album;
import com.nefziraed.entities.Artiste;
import com.nefziraed.repositories.AlbumRepository;
import com.nefziraed.repositories.ArtisteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtisteRepository artisteRepository;

    @Override
    @Transactional
    public Album addAlbumWithArtist(Album album) {
        if (album.getArtisteNom() != null) {
            Artiste artiste = artisteRepository.findByNom(album.getArtisteNom())
                    .orElseThrow(() -> new RuntimeException("Artiste not found: " + album.getArtisteNom()));
            album.setArtiste(artiste);
            artiste.getAlbums().add(album);
        }
        return albumRepository.save(album);
    }

    @Override
    @Scheduled(fixedDelay = 180000) // 3 minutes = 180000 ms
    public void detectPopularAlbums() {
        List<Album> popularAlbums = albumRepository.findPopularAlbums(200);
        log.info("=== Albums Populaires (>= 200 écoutes) ===");
        for (Album album : popularAlbums) {
            log.info("Album: {} - Écoutes: {} - Artiste: {}",
                    album.getTitre(),
                    album.getNombreEcoutes(),
                    album.getArtiste() != null ? album.getArtiste().getNom() : "N/A");
        }
        log.info("==========================================");
    }

    @Override
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }
}
