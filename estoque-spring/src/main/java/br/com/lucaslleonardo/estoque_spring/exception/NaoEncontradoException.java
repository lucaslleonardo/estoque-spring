package br.com.lucaslleonardo.estoque_spring.exception;


public class NaoEncontradoException extends Exception {

        public NaoEncontradoException(String mensagem){
            super(mensagem);
        }

}
