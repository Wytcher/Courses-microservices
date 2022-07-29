package br.com.apigateway.jwt;

public interface TokenService {

    boolean isTokenValid(String token);

    String getTokenUsername(String token);
}
