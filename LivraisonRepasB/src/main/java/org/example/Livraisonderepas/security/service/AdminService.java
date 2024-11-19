package org.example.Livraisonderepas.security.service;

import org.example.Livraisonderepas.model.Repas;
import org.example.Livraisonderepas.security.jwt.JwtResponse;
import org.example.Livraisonderepas.security.jwt.JwtUtil;
import org.example.Livraisonderepas.security.model.Role;
import org.example.Livraisonderepas.security.model.User;
import org.example.Livraisonderepas.security.repository.UserDao;
import org.example.Livraisonderepas.service.RepasService;  // Import RepasService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AdminService implements UserService {

    private UserDao userDao;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    private RepasService repasService;  // Declare RepasService

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Autowired
    public void setRepasService(RepasService repasService) {  // Inject RepasService
        this.repasService = repasService;
    }

    @Override
    public JwtResponse signIn(User user) {
        final Authentication authentication;
        User loadUserByUsername = loadUserByUsername(user.getUsername());
        authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        String token = jwtUtil.generateToken((UserDetails) authentication.getPrincipal());
        String role = loadUserByUsername.getAuthorities().iterator().next().getAuthority(); // Get the role of the user
        Long userId = loadUserByUsername.getId();
        JwtResponse response = new JwtResponse(token, role, userId);
        return response;
    }

    // Save Admin
    @Override
    public User save(User admin) {
        if (userDao.findByUsername(admin.getUsername()) != null) return null;

        Role role = roleService.findByAuthority("ADMIN");
        if (role == null) role = new Role("ADMIN");

        Collection<Role> roles = new ArrayList<>();
        roles.add(role);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setAuthorities(roles);
        roleService.save(role);
        userDao.save(admin);
        return admin;
    }


    // CRUD methods for Repas

    // Create a new repas
    public Repas createRepas(Repas repas) {
        return repasService.createRepas(repas);  // Delegate to RepasService
    }

    // Get all repases
    public List<Repas> getAllRepases() {
        return repasService.getAllRepases();  // Delegate to RepasService
    }

    // Get a repas by id
    public Repas getRepasById(Long id) {
        return repasService.getRepasById(id);  // Delegate to RepasService
    }

    // Update a repas by id
    public Repas updateRepas(Long id, Repas repasDetails) {
        return repasService.updateRepas(id, repasDetails);  // Delegate to RepasService
    }

    // Delete a repas by id
    public boolean deleteRepas(Long id) {
        return repasService.deleteRepas(id);  // Delegate to RepasService
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user " + username + " not found");
        }
        return user;
    }

    @Override
    public Collection<User> findByRole(String role) {
        return userDao.findByRole(role);
    }

    @Override
    @Transactional
    public int deleteByUsername(String username) {
        User loadedUser = userDao.findByUsername(username);
        if (loadedUser == null) {
            return -1;
        } else {
            userDao.delete(loadedUser);
            return 1;
        }
    }
}
