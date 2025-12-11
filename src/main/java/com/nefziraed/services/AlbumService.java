package com.nefziraed.services;

import com.nefziraed.entities.Album;

import java.util.List;

public interface AlbumService {

    Album addAlbumWithArtist(Album album);

    void detectPopularAlbums();

    List<Album> getAllAlbums();
}
