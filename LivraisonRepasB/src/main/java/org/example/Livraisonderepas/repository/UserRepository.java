package org.example.Livraisonderepas.repository;

import org.example.Livraisonderepas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);


    List<User> findByRole(String role);

    User findByToken(String token);
}
