package com.example.API_Jogos_Brasileirao_2024.exception;

public class JogoNaoEncontradoException extends RuntimeException {
    public JogoNaoEncontradoException(String time) {
        super("Não foram encontrados jogos do " + time);
    }
}
