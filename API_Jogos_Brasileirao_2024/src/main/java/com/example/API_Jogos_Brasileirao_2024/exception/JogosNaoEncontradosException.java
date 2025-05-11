package com.example.API_Jogos_Brasileirao_2024.exception;

public class JogosNaoEncontradosException extends RuntimeException {
    public JogosNaoEncontradosException() {
        super("Não foram encontrados jogos");
    }

    public JogosNaoEncontradosException(String time) {
        super("Não foram encontrados jogos do " + time);
    }
}
