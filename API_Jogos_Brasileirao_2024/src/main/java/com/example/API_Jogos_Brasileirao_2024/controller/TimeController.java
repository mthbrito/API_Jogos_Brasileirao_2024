package com.example.API_Jogos_Brasileirao_2024.controller;

import com.example.API_Jogos_Brasileirao_2024.exception.DadosNaoEncontradosException;
import com.example.API_Jogos_Brasileirao_2024.exception.TimeNaoEncontradoException;
import com.example.API_Jogos_Brasileirao_2024.model.Time;
import com.example.API_Jogos_Brasileirao_2024.repository.TimeRepository;
import com.example.API_Jogos_Brasileirao_2024.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<String>> getTimes() {
        try {
            return ResponseEntity.ok(timeService.buscarTimes()); //200
        } catch (DadosNaoEncontradosException e) {
            return ResponseEntity.notFound().build(); //404
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build(); //503
        }
    }

    @GetMapping("dados/times")
    public ResponseEntity<List<Time>> getDadosTimes() {
        try {
            return ResponseEntity.ok(timeService.buscarDadosTimes()); //200
        } catch (DadosNaoEncontradosException e) {
            return ResponseEntity.notFound().build(); //404
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build(); //503
        }
    }

    @GetMapping("dados/time/{time}")
    public ResponseEntity<Time> getDadosTime(@PathVariable("time") String time) {
        try {
            return ResponseEntity.ok(timeService.buscarDadosPorTime(time)); //200
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); //400
        } catch (TimeNaoEncontradoException e) {
            return ResponseEntity.notFound().build(); //404
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build(); //503
        }
    }

}
