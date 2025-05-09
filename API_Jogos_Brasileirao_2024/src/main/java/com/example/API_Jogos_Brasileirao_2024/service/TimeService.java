package com.example.API_Jogos_Brasileirao_2024.service;

import com.example.API_Jogos_Brasileirao_2024.model.Time;
import com.example.API_Jogos_Brasileirao_2024.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeService {

    @Autowired
    private final TimeRepository timeRepository;

    public TimeService(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    public List<String> buscarTimes() {
        return timeRepository.buscarTimes();
    }

    public List<Time> buscarDadosTimes() {
        return timeRepository.buscarDadosTimes();
    }

    public Time buscarDadosPorTime(String time) {
        return timeRepository.buscarDadosPorTime(time);
    }
}
