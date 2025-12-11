package com.nefziraed.services;

import com.nefziraed.entities.Album;
import com.nefziraed.entities.Utilisateur;
import com.nefziraed.repositories.AlbumRepository;
import com.nefziraed.repositories.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final AlbumRepository albumRepository;

    @Override
    @Transactional
    public Utilisateur addAlbumsToUser(Utilisateur utilisateur) {
        // Check if user already exists
        Utilisateur existingUser = utilisateurRepository
                .findByPrenomAndNom(utilisateur.getPrenom(), utilisateur.getNom())
                .orElse(null);

        if (existingUser == null) {
            existingUser = Utilisateur.builder()
                    .prenom(utilisateur.getPrenom())
                    .nom(utilisateur.getNom())
                    .albums(new ArrayList<>())
                    .build();
        }

        // Add albums and increment nombreEcoutes
        for (Album albumRequest : utilisateur.getAlbums()) {
            Album album = albumRepository.findByTitre(albumRequest.getTitre())
                    .orElseThrow(() -> new RuntimeException("Album not found: " + albumRequest.getTitre()));

            // Increment nombreEcoutes
            album.setNombreEcoutes(album.getNombreEcoutes() + 1);
            albumRepository.save(album);

            // Add album to user if not already associated
            if (!existingUser.getAlbums().contains(album)) {
                existingUser.getAlbums().add(album);
                album.getUtilisateurs().add(existingUser);
            }
        }

        return utilisateurRepository.save(existingUser);
    }

    @Override
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }
}
