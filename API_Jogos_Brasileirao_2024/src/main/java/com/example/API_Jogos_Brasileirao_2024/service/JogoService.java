package com.example.API_Jogos_Brasileirao_2024.service;

import com.example.API_Jogos_Brasileirao_2024.exception.DadosNaoEncontradosException;
import com.example.API_Jogos_Brasileirao_2024.exception.JogoNaoEncontradoException;
import com.example.API_Jogos_Brasileirao_2024.model.Jogo;
import com.example.API_Jogos_Brasileirao_2024.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogoService {

    @Autowired
    private final JogoRepository jogoRepository;

    public JogoService(JogoRepository jogoRepository) {
        this.jogoRepository = jogoRepository;
    }

    public List<Jogo> buscarJogos() throws DataAccessException {
        List<Jogo> jogos = jogoRepository.buscarJogos();
        if (jogos.isEmpty()) {
            throw new DadosNaoEncontradosException();
        }
        return jogos;
    }

    public List<Jogo> buscarJogosPorRodada(String rodada) throws DataAccessException {
        if (!rodada.matches("^\\d+$")) {
            throw new IllegalArgumentException("Entrada inválida!");
        }
        List<Jogo> jogos = jogoRepository.buscarJogosPorRodada(rodada);
        if (jogos.isEmpty()) {
            throw new DadosNaoEncontradosException();
        }
        return jogos;
    }

    public List<Jogo> buscarJogosPorTime(String time) throws DataAccessException {
        if (!time.matches("^[\\p{L}\\s-]+$")) {
            throw new IllegalArgumentException("Entrada inválida!");
        }
        List<Jogo> jogos = jogoRepository.buscarJogosPorTime(time);
        if (jogos.isEmpty()) {
            throw new JogoNaoEncontradoException(time);
        }
        return jogos;
    }

    public List<Jogo> buscarJogosPorTimeMandante(String time) throws DataAccessException {
        if (!time.matches("^[\\p{L}\\s-]+$")) {
            throw new IllegalArgumentException("Entrada inválida!");
        }
        List<Jogo> jogos = jogoRepository.buscarJogosPorTimeMandante(time);
        if (jogos.isEmpty()) {
            throw new JogoNaoEncontradoException(time);
        }
        return jogos;
    }

    public List<Jogo> buscarJogosPorTimeVisitante(String time) throws DataAccessException {
        if (!time.matches("^[\\p{L}\\s-]+$")) {
            throw new IllegalArgumentException("Entrada inválida!");
        }
        List<Jogo> jogos = jogoRepository.buscarJogosPorTimeVisitante(time);
        if (jogos.isEmpty()) {
            throw new JogoNaoEncontradoException(time);
        }
        return jogos;
    }

    public List<Jogo> buscarVitoriasPorTime(String time) throws DataAccessException {
        if (!time.matches("^[\\p{L}\\s-]+$")) {
            throw new IllegalArgumentException("Entrada inválida!");
        }
        List<Jogo> jogos = jogoRepository.buscarVitoriasPorTime(time);
        if (jogos.isEmpty()) {
            throw new JogoNaoEncontradoException(time);
        }
        return jogos;
    }

    public List<Jogo> buscarEmpatesPorTime(String time) throws DataAccessException {
        if (!time.matches("^[\\p{L}\\s-]+$")) {
            throw new IllegalArgumentException("Entrada inválida!");
        }
        List<Jogo> jogos = jogoRepository.buscarEmpatesPorTime(time);
        if (jogos.isEmpty()) {
            throw new JogoNaoEncontradoException(time);
        }
        return jogos;
    }

    public List<Jogo> buscarDerrotasPorTime(String time) throws DataAccessException {
        if (!time.matches("^[\\p{L}\\s-]+$")) {
            throw new IllegalArgumentException("Entrada inválida!");
        }
        List<Jogo> jogos = jogoRepository.buscarDerrotasPorTime(time);
        if (jogos.isEmpty()) {
            throw new JogoNaoEncontradoException(time);
        }
        return jogos;
    }
}
