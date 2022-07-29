package br.com.authapi.services.jwt;

import org.springframework.security.core.Authentication;

public interface TokenService {

    String generateToken(Authentication authentication);

    boolean isTokenValid(String token);

    String getTokenUsername(String token);
}
