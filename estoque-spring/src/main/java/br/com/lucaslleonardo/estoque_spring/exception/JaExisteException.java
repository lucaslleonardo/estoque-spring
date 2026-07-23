package br.com.lucaslleonardo.estoque_spring.exception;

public class JaExisteException extends RuntimeException {
    public JaExisteException(String message) {
        super(message);
    }
}
