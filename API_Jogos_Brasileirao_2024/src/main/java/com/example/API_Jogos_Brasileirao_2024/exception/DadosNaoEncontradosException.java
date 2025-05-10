package com.example.API_Jogos_Brasileirao_2024.exception;

public class DadosNaoEncontradosException extends RuntimeException {
    public DadosNaoEncontradosException() {
        super("Dados não encontrados");
    }
}
