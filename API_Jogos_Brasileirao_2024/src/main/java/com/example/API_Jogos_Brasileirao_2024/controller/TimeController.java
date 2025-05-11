package com.example.API_Jogos_Brasileirao_2024.controller;

import com.example.API_Jogos_Brasileirao_2024.exception.*;

import com.example.API_Jogos_Brasileirao_2024.model.Time;
import com.example.API_Jogos_Brasileirao_2024.service.TimeService;

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

    private final TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping("times")
    public ResponseEntity<List<String>> buscarTimes() {
        try {
            return ResponseEntity.ok(timeService.buscarTimes()); //200
        } catch (TimesNaoEncontradosException e) {
            return ResponseEntity.notFound().build(); //404
        } catch (AcessoAoBancoDeDadosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
        }
    }

    @GetMapping("dados/times")
    public ResponseEntity<List<Time>> buscarDadosTimes() {
        try {
            return ResponseEntity.ok(timeService.buscarDadosTimes()); //200
        } catch (TimesNaoEncontradosException e) {
            return ResponseEntity.notFound().build(); //404
        } catch (AcessoAoBancoDeDadosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
        }
    }

    @GetMapping("dados/time/{time}")
    public ResponseEntity<Time> buscarDadosPorTime(@PathVariable("time") String time) {
        try {
            return ResponseEntity.ok(timeService.buscarDadosPorTime(time)); //200
        } catch (EntradaInvalidaException e) {
            return ResponseEntity.badRequest().build(); //400
        } catch (TimeNaoEncontradoException e) {
            return ResponseEntity.notFound().build(); //404
        } catch (AcessoAoBancoDeDadosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
        }
    }

}
