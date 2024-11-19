package org.example.Livraisonderepas.security.service;


import org.example.Livraisonderepas.security.jwt.JwtResponse;
import org.example.Livraisonderepas.security.model.User;

import java.util.Collection;
import java.util.List;

public interface UserService {
    JwtResponse signIn(User user);
    User save(User user);
    List<User> findAll();

    Collection<User> findByRole(String role);

    int deleteByUsername(String username);


}