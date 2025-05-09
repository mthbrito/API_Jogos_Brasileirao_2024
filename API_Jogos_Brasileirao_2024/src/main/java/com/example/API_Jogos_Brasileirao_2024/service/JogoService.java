package com.example.API_Jogos_Brasileirao_2024.service;

import com.example.API_Jogos_Brasileirao_2024.model.Jogo;
import com.example.API_Jogos_Brasileirao_2024.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogoService {

    @Autowired
    private final JogoRepository jogoRepository;

    public JogoService(JogoRepository jogoRepository) {
        this.jogoRepository = jogoRepository;
    }

    public List<Jogo> buscarJogos() {
        return jogoRepository.buscarJogos();
    }

    public List<Jogo> buscarJogosPorRodada(int rodada) {
        return jogoRepository.buscarJogosPorRodada(rodada);
    }

    public List<Jogo> buscarJogosPorTime(String time) {
        return jogoRepository.buscarJogosPorTime(time);
    }

    public List<Jogo> buscarJogosPorTimeMandante(String time) {
        return jogoRepository.buscarJogosPorTimeMandante(time);
    }

    public List<Jogo> buscarJogosPorTimeVisitante(String time) {
        return jogoRepository.buscarJogosPorTimeVisitante(time);
    }

    public List<Jogo> buscarVitoriasPorTime(String time) {
        return jogoRepository.buscarVitoriasPorTime(time);
    }

    public List<Jogo> buscarEmpatesPorTime(String time) {
        return jogoRepository.buscarEmpatesPorTime(time);
    }

    public List<Jogo> buscarDerrotasPorTime(String time) {
        return jogoRepository.buscarDerrotasPorTime(time);
    }
}
