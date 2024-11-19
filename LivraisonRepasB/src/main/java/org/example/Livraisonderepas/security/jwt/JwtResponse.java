package org.example.Livraisonderepas.security.jwt;

public class JwtResponse {

    private String token;
    private String role;
    private Long userId;

    public JwtResponse(String token) {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public JwtResponse(String token, String role, Long userId ) {
        this.token = token;
        this.role = role;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}