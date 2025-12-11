package com.nefziraed.controllers;

import com.nefziraed.entities.Album;
import com.nefziraed.entities.Artiste;
import com.nefziraed.entities.Utilisateur;
import com.nefziraed.services.AlbumService;
import com.nefziraed.services.ArtisteService;
import com.nefziraed.services.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/streaming")
@RequiredArgsConstructor
public class StreamingController {

    private final ArtisteService artisteService;
    private final AlbumService albumService;
    private final UtilisateurService utilisateurService;

    // a) Ajouter des Artistes
    @PostMapping("/artistes")
    public ResponseEntity<Artiste> addArtiste(@RequestBody Artiste artiste) {
        return new ResponseEntity<>(artisteService.addArtiste(artiste), HttpStatus.CREATED);
    }

    // b) Ajouter des Albums avec Artiste
    @PostMapping("/albums")
    public ResponseEntity<Album> addAlbumWithArtist(@RequestBody Album album) {
        return new ResponseEntity<>(albumService.addAlbumWithArtist(album), HttpStatus.CREATED);
    }

    // c) Associer des Albums à un Utilisateur
    @PostMapping("/utilisateurs/albums")
    public ResponseEntity<Utilisateur> addAlbumsToUser(@RequestBody Utilisateur utilisateur) {
        return new ResponseEntity<>(utilisateurService.addAlbumsToUser(utilisateur), HttpStatus.CREATED);
    }

    // d) Détecter les Albums Populaires (manual trigger)
    @GetMapping("/albums/popular")
    public ResponseEntity<String> detectPopularAlbums() {
        albumService.detectPopularAlbums();
        return ResponseEntity.ok("Popular albums detection triggered. Check logs for results.");
    }

    // e) Calculate Royalties (triggers AOP performance measurement)
    @PostMapping("/royalties/calculate")
    public ResponseEntity<String> calculateRoyalties() {
        artisteService.calculateRoyalties();
        return ResponseEntity.ok("Royalties calculated. Check logs for performance metrics.");
    }

    // f) Liste des Artistes par Royalties
    @GetMapping("/artistes/royalties")
    public ResponseEntity<List<String>> getArtistsByRoyalties() {
        return ResponseEntity.ok(artisteService.getArtistsByRoyalties());
    }

    // Additional endpoints for convenience
    @GetMapping("/artistes")
    public ResponseEntity<List<Artiste>> getAllArtistes() {
        return ResponseEntity.ok(artisteService.getArtistsByRoyalties().stream()
                .map(nom -> Artiste.builder().nom(nom).build())
                .toList());
    }

    @GetMapping("/albums")
    public ResponseEntity<List<Album>> getAllAlbums() {
        return ResponseEntity.ok(albumService.getAllAlbums());
    }

    @GetMapping("/utilisateurs")
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs() {
        return ResponseEntity.ok(utilisateurService.getAllUtilisateurs());
    }
}
