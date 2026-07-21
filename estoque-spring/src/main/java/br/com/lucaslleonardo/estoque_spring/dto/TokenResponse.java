package br.com.lucaslleonardo.estoque_spring.dto;

public record TokenResponse(String token, Long expiresIn) {
}
