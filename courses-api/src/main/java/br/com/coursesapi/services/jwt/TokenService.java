package br.com.coursesapi.services.jwt;

public interface TokenService {

    boolean isTokenValid(String token);

    String getTokenUsername(String token);
}
