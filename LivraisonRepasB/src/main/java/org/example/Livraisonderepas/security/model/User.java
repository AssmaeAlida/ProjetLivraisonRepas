
package org.example.Livraisonderepas.security.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User implements UserDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String nom;
        private String prenom;
        private String email;
        private String password;
        private String role;
        private String numTel;


        // String security attributes

        private String username;

        //boolean
        private boolean accountNonExpired = true;
        private boolean accountNonLocked = true;
        private boolean credentialsNonExpired = true;
        private boolean enabled = true;

        @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        private Collection<Role> authorities;

        //    Constructors

        //    Getters & Setters

        @Override
        public Collection<Role> getAuthorities() {
                return authorities;
        }

        @Override
        public String getPassword() {
                return password;
        }

        @Override
        public String getUsername() {
                return this.username;
        }

        @Override
        public boolean isAccountNonExpired() {
                return accountNonExpired;
        }

        @Override
        public boolean isAccountNonLocked() {
                return accountNonLocked;
        }

        @Override
        public boolean isCredentialsNonExpired() {
                return credentialsNonExpired;
        }

        @Override
        public boolean isEnabled() {
                return enabled;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getNom() {
                return nom;
        }

        public void setNom(String nom) {
                this.nom = nom;
        }

        public String getPrenom() {
                return prenom;
        }

        public void setPrenom(String prenom) {
                this.prenom = prenom;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getRole() {
                return role;
        }

        public void setRole(String role) {
                this.role = role;
        }

        public void setAccountNonExpired(boolean accountNonExpired) {
                this.accountNonExpired = accountNonExpired;
        }

        public void setAccountNonLocked(boolean accountNonLocked) {
                this.accountNonLocked = accountNonLocked;
        }

        public void setCredentialsNonExpired(boolean credentialsNonExpired) {
                this.credentialsNonExpired = credentialsNonExpired;
        }

        public void setEnabled(boolean enabled) {
                this.enabled = enabled;
        }

        public void setAuthorities(Collection<Role> authorities) {
                this.authorities = authorities;
        }

        public String getNumTel() {
                return numTel;
        }

        public void setNumTel(String numTel) {
                this.numTel = numTel;
        }
}
