package br.com.lucaslleonardo.estoque_spring.handler;


public class NaoEncontradoException extends Exception {

        public NaoEncontradoException(String mensagem){
            super(mensagem);
        }

}
