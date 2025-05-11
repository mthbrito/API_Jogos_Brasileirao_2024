package com.example.API_Jogos_Brasileirao_2024.service;

import com.example.API_Jogos_Brasileirao_2024.exception.*;
import com.example.API_Jogos_Brasileirao_2024.model.Time;
import com.example.API_Jogos_Brasileirao_2024.repository.TimeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeService {

    private final TimeRepository timeRepository;

    public TimeService(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    public List<String> buscarTimes() throws AcessoAoBancoDeDadosException {
        List<String> times = timeRepository.buscarTimes();
        checarSeTimesExistem(times);
        return times;
    }

    public List<Time> buscarDadosTimes()  throws AcessoAoBancoDeDadosException  {
        List<Time> dadosTimes = timeRepository.buscarDadosTimes();
        checarSeTimesExistem(dadosTimes);
        return dadosTimes;
    }

    public Time buscarDadosPorTime(String time) throws AcessoAoBancoDeDadosException {
        checarSeTimeEValido(time);
        Time dadosTime = timeRepository.buscarDadosPorTime(time);
        checarSeTimeExiste(dadosTime, time);
        return dadosTime;
    }

    private void checarSeTimeEValido(String time) {
        if (time == null || (!time.matches("^[\\p{L}\\s-]+$"))) {
            throw new EntradaInvalidaException();
        }
    }

    private void checarSeTimeExiste(Time time, String nomeTime) {
        if (time == null) {
            throw new TimeNaoEncontradoException(nomeTime);
        }
    }

    private <T> void checarSeTimesExistem(List<T> times) {
        if(times.isEmpty()) {
            throw new TimesNaoEncontradosException();
        }
    }
}
