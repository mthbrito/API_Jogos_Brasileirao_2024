package com.example.API_Jogos_Brasileirao_2024.service;

import com.example.API_Jogos_Brasileirao_2024.exception.DadosNaoEncontradosException;
import com.example.API_Jogos_Brasileirao_2024.exception.TimeNaoEncontradoException;
import com.example.API_Jogos_Brasileirao_2024.model.Time;
import com.example.API_Jogos_Brasileirao_2024.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeService {

    @Autowired
    private final TimeRepository timeRepository;

    public TimeService(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    public List<String> buscarTimes() throws DataAccessException {
        List<String> times = timeRepository.buscarTimes();
        if (times.isEmpty()) {
            throw new DadosNaoEncontradosException();
        }
        return times;
    }

    public List<Time> buscarDadosTimes() throws DataAccessException {
        List<Time> dadosTimes = timeRepository.buscarDadosTimes();
        if (dadosTimes.isEmpty()) {
            throw new DadosNaoEncontradosException();
        }
        return dadosTimes;
    }

    public Time buscarDadosPorTime(String time) throws TimeNaoEncontradoException, DataAccessException {
        if (!time.matches("^[\\p{L}\\s-]+$")) {
            throw new IllegalArgumentException("Entrada inválida!");
        }
        return timeRepository.buscarDadosPorTime(time);
    }
}
