package org.example.Livraisonderepas.security.common;

public interface SecurityParams {
    static final String JWT_HEADER_NAME = "Authorization";
    static final String SECRET = "b36d9f84-e0e9-4a3f-94ca-c18a6046eef2";
    static final long EXPIRATION = 86400000;
    static final String HEADER_PREFIX = "Bearer ";
}
