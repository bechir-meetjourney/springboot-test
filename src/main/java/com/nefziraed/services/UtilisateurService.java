package com.nefziraed.services;

import com.nefziraed.entities.Utilisateur;

import java.util.List;

public interface UtilisateurService {

    Utilisateur addAlbumsToUser(Utilisateur utilisateur);

    List<Utilisateur> getAllUtilisateurs();
}
