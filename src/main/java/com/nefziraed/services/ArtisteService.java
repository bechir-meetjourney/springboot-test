package com.nefziraed.services;

import com.nefziraed.entities.Artiste;

import java.util.List;

public interface ArtisteService {

    Artiste addArtiste(Artiste artiste);

    List<String> getArtistsByRoyalties();

    void calculateRoyalties();
}
