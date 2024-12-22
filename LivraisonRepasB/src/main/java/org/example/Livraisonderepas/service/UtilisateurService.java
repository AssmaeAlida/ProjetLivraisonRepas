package org.example.Livraisonderepas.service;

import org.example.Livraisonderepas.model.Role;
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

    public int signUp(String fullName,String email, String password) {
        User existingUser = userRepository.findByEmail(email);
        if (existingUser == null) {
            User newUser = new User();
            newUser.setFullName(fullName);
            newUser.setEmail(email);
            newUser.setPassword(passwordEncoder.encode(password)); // Hash the password before storing
            userRepository.save(newUser);
            return 1;
        } else {
            return -1;
        }
    }



    @Transactional
    public User addLivreur(User livreur) {
        User existingUser = userRepository.findByEmail(livreur.getEmail());
        if (existingUser != null) {
            throw new RuntimeException("User with email " + livreur.getEmail() + " already exists");
        }
        livreur.setFullName(livreur.getFullName());
        livreur.setEmail(livreur.getEmail());
        livreur.setRole(Role.LIVREUR);
        livreur.setLieu(livreur.getLieu());
        livreur.setDatenaissance(livreur.getDatenaissance());
        livreur.setNumTel(livreur.getNumTel());
        livreur.setPassword(passwordEncoder.encode(livreur.getPassword())); // Hash the password before storing
        return userRepository.save(livreur);
    }


//Client et Livreur
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
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


    @Transactional
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
public User updateUser(User updatedUser) {//Pour Livreur aussi
    User existingUser = userRepository.findById(updatedUser.getId())
            .orElseThrow(() -> new RuntimeException("User not found."));
    if (updatedUser.getFullName() != null) {
        existingUser.setFullName(updatedUser.getFullName());
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
    if (updatedUser.getNumTel() != null) {
          existingUser.setNumTel(updatedUser.getNumTel());
    }

    if (updatedUser.getLieu() != null) {
            existingUser.setLieu(updatedUser.getLieu());
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
