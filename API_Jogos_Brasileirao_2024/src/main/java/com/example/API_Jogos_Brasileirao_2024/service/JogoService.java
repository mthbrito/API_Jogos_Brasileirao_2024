package com.example.API_Jogos_Brasileirao_2024.service;

import com.example.API_Jogos_Brasileirao_2024.exception.AcessoAoBancoDeDadosException;
import com.example.API_Jogos_Brasileirao_2024.exception.EntradaInvalidaException;
import com.example.API_Jogos_Brasileirao_2024.exception.JogosNaoEncontradosException;
import com.example.API_Jogos_Brasileirao_2024.model.Jogo;
import com.example.API_Jogos_Brasileirao_2024.repository.JogoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogoService {

    private final JogoRepository jogoRepository;

    public JogoService(JogoRepository jogoRepository) {
        this.jogoRepository = jogoRepository;
    }

    public List<Jogo> buscarJogos() throws AcessoAoBancoDeDadosException {
        List<Jogo> jogos = jogoRepository.buscarJogos();
        checarSeJogosExistem(jogos);
        return jogos;
    }

    public List<Jogo> buscarJogosPorRodada(String rodada) throws AcessoAoBancoDeDadosException {
        checarSeRodadaEValida(rodada);
        List<Jogo> jogos = jogoRepository.buscarJogosPorRodada(rodada);
        checarSeJogosExistem(jogos);
        return jogos;
    }

    public List<Jogo> buscarJogosPorTime(String time) throws AcessoAoBancoDeDadosException {
        checarSeTimeEValido(time);
        List<Jogo> jogos = jogoRepository.buscarJogosPorTime(time);
        checarSeJogosExistem(jogos, time);
        return jogos;
    }

    public List<Jogo> buscarJogosPorTimeMandante(String time) throws AcessoAoBancoDeDadosException {
        checarSeTimeEValido(time);
        List<Jogo> jogos = jogoRepository.buscarJogosPorTimeMandante(time);
        checarSeJogosExistem(jogos, time);
        return jogos;
    }

    public List<Jogo> buscarJogosPorTimeVisitante(String time) throws AcessoAoBancoDeDadosException {
        checarSeTimeEValido(time);
        List<Jogo> jogos = jogoRepository.buscarJogosPorTimeVisitante(time);
        checarSeJogosExistem(jogos, time);
        return jogos;
    }

    public List<Jogo> buscarVitoriasPorTime(String time) throws AcessoAoBancoDeDadosException {
        checarSeTimeEValido(time);
        List<Jogo> jogos = jogoRepository.buscarVitoriasPorTime(time);
        checarSeJogosExistem(jogos, time);
        return jogos;
    }

    public List<Jogo> buscarEmpatesPorTime(String time) throws AcessoAoBancoDeDadosException {
        checarSeTimeEValido(time);
        List<Jogo> jogos = jogoRepository.buscarEmpatesPorTime(time);
        checarSeJogosExistem(jogos, time);
        return jogos;
    }

    public List<Jogo> buscarDerrotasPorTime(String time) throws AcessoAoBancoDeDadosException {
        checarSeTimeEValido(time);
        List<Jogo> jogos = jogoRepository.buscarDerrotasPorTime(time);
        checarSeJogosExistem(jogos, time);
        return jogos;
    }

    private void checarSeTimeEValido(String time) {
        if (time == null || (!time.matches("^[\\p{L}\\s-]+$"))) {
            throw new EntradaInvalidaException();
        }
    }

    private void checarSeRodadaEValida(String rodada) {
        if (rodada == null || (!rodada.matches("^\\d+$"))) {
            throw new EntradaInvalidaException();
        }
    }

    private void checarSeJogosExistem(List<Jogo> jogos, String time) {
        if (jogos.isEmpty()) {
            throw new JogosNaoEncontradosException(time);
        }
    }

    private void checarSeJogosExistem(List<Jogo> jogos) {
        if (jogos.isEmpty()) {
            throw new JogosNaoEncontradosException();
        }
    }
}
