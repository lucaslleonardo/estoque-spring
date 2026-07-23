package br.com.lucaslleonardo.estoque_spring.handler;

import lombok.*;

public record ErrorResponse(Integer status, String message) {
}
