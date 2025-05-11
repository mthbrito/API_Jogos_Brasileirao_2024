package com.example.API_Jogos_Brasileirao_2024.exception;

import org.springframework.dao.DataAccessException;

public class AcessoAoBancoDeDadosException extends DataAccessException {
    public AcessoAoBancoDeDadosException(String mensagem) {
        super(mensagem);
    }
}
