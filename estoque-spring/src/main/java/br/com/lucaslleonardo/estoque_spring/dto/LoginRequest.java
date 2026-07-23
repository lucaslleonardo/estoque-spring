package br.com.lucaslleonardo.estoque_spring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank @Email(message = "Campo obrigatório") String email, @NotBlank(message = "Campo obrigatório") String password) {
}
