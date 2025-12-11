package com.nefziraed.services;

import com.nefziraed.entities.Album;
import com.nefziraed.entities.Artiste;
import com.nefziraed.repositories.ArtisteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArtisteServiceImpl implements ArtisteService {

    private final ArtisteRepository artisteRepository;

    @Override
    @Transactional
    public Artiste addArtiste(Artiste artiste) {
        return artisteRepository.save(artiste);
    }

    @Override
    public List<String> getArtistsByRoyalties() {
        return artisteRepository.findAllArtistesOrderByRoyaltiesDesc();
    }

    @Override
    @Transactional
    public void calculateRoyalties() {
        List<Artiste> artistes = artisteRepository.findAll();
        for (Artiste artiste : artistes) {
            double totalRoyalties = 0.0;
            for (Album album : artiste.getAlbums()) {
                totalRoyalties += album.getPrix() * album.getNombreEcoutes() * 0.01;
            }
            artiste.setTotalRoyalties(artiste.getTotalRoyalties() + totalRoyalties);
            artisteRepository.save(artiste);
        }
        log.info("Royalties calculated for all artists");
    }
}
