package com.example.API_Jogos_Brasileirao_2024.exception;

public class TimesNaoEncontradosException extends RuntimeException {
    public TimesNaoEncontradosException() {
        super("Não foram encontrados times");
    }
}
