package com.example.API_Jogos_Brasileirao_2024.controller;

import com.example.API_Jogos_Brasileirao_2024.model.Time;
import com.example.API_Jogos_Brasileirao_2024.repository.TimeRepository;
import com.example.API_Jogos_Brasileirao_2024.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/brasileirao2024")
public class TimeController {

    @Autowired
    private final TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping("times")
    public List<String> getTimes() {
        return timeService.buscarTimes();
    }

    @GetMapping("dados/times")
    public List<Time> getDadosTimes() {
        return timeService.buscarDadosTimes();
    }

    @GetMapping("dados/time/{time}")
    public Time getDadosTime(@PathVariable("time") String time) {
        return timeService.buscarDadosPorTime(time);
    }

}
