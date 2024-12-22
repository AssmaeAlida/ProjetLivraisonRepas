package org.example.Livraisonderepas.repository;

import org.example.Livraisonderepas.model.Role;
import org.example.Livraisonderepas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByToken(String token);

    List<User> findByRole(Role role);
}
