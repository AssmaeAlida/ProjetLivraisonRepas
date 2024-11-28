package org.example.Livraisonderepas.service;

import org.example.Livraisonderepas.model.User;
import org.example.Livraisonderepas.repository.UserRepository;
import org.example.Livraisonderepas.service.Mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;


import java.util.List;
import java.util.Random;

@Service
public class UtilisateurService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailService mailService;


    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User signIn(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    public int signUp(String email, String password) {
        User existingUser = userRepository.findByEmail(email);
        if (existingUser == null) {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setPassword(passwordEncoder.encode(password)); // Hash the password before storing
            userRepository.save(newUser);
            return 1;
        } else {
            return -1;
        }
    }

    public List<User> findByRole(String role) {
        return userRepository.findByRole(role);
    }

    @Transactional
    public User addUtilisateur(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new RuntimeException("User with email " + user.getEmail() + " already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash the password before storing
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User forgotPassword(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            // Generate a token with 8 numbers
            Random random = new Random();
            String token = String.format("%08d", random.nextInt(100000000));
            user.setToken(token);
            userRepository.save(user);
            try {
                mailService.sendMail(email, "Password Reset", "Your reset token is: " + token);
            } catch (MessagingException e) {
                System.out.println("Error sending email: " + e.getMessage());
            }
            return user;
        }
        return null;
    }


    public User changePassword(String token, String newPassword) {
        User user = userRepository.findByToken(token);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
            user.setToken(null);
            return userRepository.save(user);
        }
        return null;
    }


    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }


  @Transactional
public User updateUser(User updatedUser) {
    User existingUser = userRepository.findById(updatedUser.getId())
            .orElseThrow(() -> new RuntimeException("User not found."));
    if (updatedUser.getNom() != null) {
        existingUser.setNom(updatedUser.getNom());
    }
    if (updatedUser.getPrenom() != null) {
        existingUser.setPrenom(updatedUser.getPrenom());
    }
    if (updatedUser.getRole() != null) {
        existingUser.setRole(updatedUser.getRole());
    }
    if (updatedUser.getEmail() != null) {
        existingUser.setEmail(updatedUser.getEmail());
    }
    if (updatedUser.getPassword() != null) {
        existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
    }
    // Add other fields like name, address, etc.
    return userRepository.save(existingUser);
}


    @Transactional
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));
        userRepository.delete(existingUser);
    }
}
