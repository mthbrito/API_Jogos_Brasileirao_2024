package com.example.API_Jogos_Brasileirao_2024.exception;

public class TimeNaoEncontradoException extends RuntimeException {
    public TimeNaoEncontradoException(String time) {
        super(time + " não foi encontrado");
    }
}
