package com.example.API_Jogos_Brasileirao_2024.controller;

import com.example.API_Jogos_Brasileirao_2024.exception.DadosNaoEncontradosException;
import com.example.API_Jogos_Brasileirao_2024.exception.JogoNaoEncontradoException;
import com.example.API_Jogos_Brasileirao_2024.model.Jogo;
import com.example.API_Jogos_Brasileirao_2024.service.JogoService;
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
public class JogoController {

    @Autowired
    private final JogoService jogoService;

    public JogoController(JogoService jogoService) {
        this.jogoService = jogoService;
    }

    @GetMapping("dados/jogos")
    public ResponseEntity<List<Jogo>> getDadosJogos() {
        try {
            return ResponseEntity.ok(jogoService.buscarJogos()); //200
        } catch (DadosNaoEncontradosException e) {
            return ResponseEntity.notFound().build(); //404
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build(); //503
        }
    }

    @GetMapping("dados/jogos/rodada/{rodada}")
    public ResponseEntity<List<Jogo>> getDadosJogosPorRodada(@PathVariable("rodada") String rodada) {
        try {
            return ResponseEntity.ok(jogoService.buscarJogosPorRodada(rodada)); //200
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); //400
        } catch (DadosNaoEncontradosException e) {
            return ResponseEntity.notFound().build(); //404
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build(); //503
        }
    }

    @GetMapping("dados/jogos/{time}")
    public ResponseEntity<List<Jogo>> getDadosJogosPorTime(@PathVariable("time") String time) {
        try {
            return ResponseEntity.ok(jogoService.buscarJogosPorTime(time)); //200
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); //400
        } catch (JogoNaoEncontradoException e) {
            return ResponseEntity.notFound().build(); //404
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build(); //503
        }
    }

    @GetMapping("dados/jogos/{time}/mandante")
    public ResponseEntity<List<Jogo>> getDadosJogosPorTimeMandante(@PathVariable("time") String time) {
        try {
            return ResponseEntity.ok(jogoService.buscarJogosPorTimeMandante(time)); //200
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); //400
        } catch (JogoNaoEncontradoException e) {
            return ResponseEntity.notFound().build(); //404
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build(); //503
        }
    }

    @GetMapping("dados/jogos/{time}/visitante")
    public ResponseEntity<List<Jogo>> getDadosJogosPorTimeVisitante(@PathVariable("time") String time) {
        try {
            return ResponseEntity.ok(jogoService.buscarJogosPorTimeVisitante(time)); //200
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); //400
        } catch (JogoNaoEncontradoException e) {
            return ResponseEntity.notFound().build(); //404
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build(); //503
        }
    }

    @GetMapping("dados/jogos/{time}/vitorias")
    public ResponseEntity<List<Jogo>> getDadosVitoriasTime(@PathVariable("time") String time) {
        try {
            return ResponseEntity.ok(jogoService.buscarVitoriasPorTime(time)); //200
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); //400
        } catch (JogoNaoEncontradoException e) {
            return ResponseEntity.notFound().build(); //404
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build(); //503
        }
    }

    @GetMapping("dados/jogos/{time}/empates")
    public ResponseEntity<List<Jogo>> getDadosEmpatesTime(@PathVariable("time") String time) {
        try {
            return ResponseEntity.ok(jogoService.buscarEmpatesPorTime(time)); //200
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); //400
        } catch (JogoNaoEncontradoException e) {
            return ResponseEntity.notFound().build(); //404
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build(); //503
        }
    }

    @GetMapping("dados/jogos/{time}/derrotas")
    public ResponseEntity<List<Jogo>> getDadosDerrotasTime(@PathVariable("time") String time) {
        try {
            return ResponseEntity.ok(jogoService.buscarDerrotasPorTime(time)); //200
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); //400
        } catch (JogoNaoEncontradoException e) {
            return ResponseEntity.notFound().build(); //404
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build(); //503
        }
    }
}
