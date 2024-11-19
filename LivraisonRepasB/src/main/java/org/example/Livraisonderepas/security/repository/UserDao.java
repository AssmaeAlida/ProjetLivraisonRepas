package org.example.Livraisonderepas.security.repository;


import org.example.Livraisonderepas.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);

    // La méthode deleteByUsername ne renvoie rien (void)
    int deleteByUsername(String username);

    Collection<User> findByRole(String role);
}
