package org.example.Livraisonderepas.security.service.impl;



import org.example.Livraisonderepas.security.jwt.JwtResponse;
import org.example.Livraisonderepas.security.model.User;
import org.example.Livraisonderepas.security.repository.UserDao;
import org.example.Livraisonderepas.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao; // Repository pour interagir avec la base de données

    @Override
    public JwtResponse signIn(User user) {
        // Implémentation de la logique de connexion
        // Par exemple : vérifiez les informations d'identification, générez un token JWT, etc.
        return new JwtResponse("example-token");
    }

    @Override
    public User save(User user) {
        // Sauvegarder un utilisateur dans la base de données
        return userDao.save(user);
    }

    @Override
    public List<User> findAll() {
        // Récupérer tous les utilisateurs
        return userDao.findAll();
    }

    @Override
    public Collection<User> findByRole(String role) {
        // Rechercher les utilisateurs par rôle
        return userDao.findByRole(role);
    }

    @Override
    public int deleteByUsername(String username) {
        // Supprimer un utilisateur par son nom d'utilisateur
        return userDao.deleteByUsername(username);
    }
}
