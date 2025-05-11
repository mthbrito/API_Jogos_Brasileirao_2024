package com.example.API_Jogos_Brasileirao_2024.controller;

import com.example.API_Jogos_Brasileirao_2024.exception.AcessoAoBancoDeDadosException;
import com.example.API_Jogos_Brasileirao_2024.exception.JogosNaoEncontradosException;
import com.example.API_Jogos_Brasileirao_2024.exception.EntradaInvalidaException;
import com.example.API_Jogos_Brasileirao_2024.model.Jogo;
import com.example.API_Jogos_Brasileirao_2024.service.JogoService;
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

    private final JogoService jogoService;

    public JogoController(JogoService jogoService) {
        this.jogoService = jogoService;
    }

    @GetMapping("dados/jogos")
    public ResponseEntity<List<Jogo>> buscarJogos() {
        try {
            return ResponseEntity.ok(jogoService.buscarJogos()); //200
        } catch (JogosNaoEncontradosException e) {
            return ResponseEntity.notFound().build(); //404
        } catch (AcessoAoBancoDeDadosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
        }
    }

    @GetMapping("dados/jogos/rodada/{rodada}")
    public ResponseEntity<List<Jogo>> buscarJogosPorRodada(@PathVariable("rodada") String rodada) {
        try {
            return ResponseEntity.ok(jogoService.buscarJogosPorRodada(rodada)); //200
        } catch (EntradaInvalidaException e) {
            return ResponseEntity.badRequest().build(); //400
        } catch (JogosNaoEncontradosException e) {
            return ResponseEntity.notFound().build(); //404
        } catch (AcessoAoBancoDeDadosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
        }
    }

    @GetMapping("dados/jogos/{time}")
    public ResponseEntity<List<Jogo>> buscarJogosPorTime(@PathVariable("time") String time) {
        try {
            return ResponseEntity.ok(jogoService.buscarJogosPorTime(time)); //200
        } catch (EntradaInvalidaException e) {
            return ResponseEntity.badRequest().build(); //400
        } catch (JogosNaoEncontradosException e) {
            return ResponseEntity.notFound().build(); //404
        } catch (AcessoAoBancoDeDadosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
        }
    }

    @GetMapping("dados/jogos/{time}/mandante")
    public ResponseEntity<List<Jogo>> buscarJogosPorTimeMandante(@PathVariable("time") String time) {
        try {
            return ResponseEntity.ok(jogoService.buscarJogosPorTimeMandante(time)); //200
        } catch (EntradaInvalidaException e) {
            return ResponseEntity.badRequest().build(); //400
        } catch (JogosNaoEncontradosException e) {
            return ResponseEntity.notFound().build(); //404
        } catch (AcessoAoBancoDeDadosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
        }
    }

    @GetMapping("dados/jogos/{time}/visitante")
    public ResponseEntity<List<Jogo>> buscarJogosPorTimeVisitante(@PathVariable("time") String time) {
        try {
            return ResponseEntity.ok(jogoService.buscarJogosPorTimeVisitante(time)); //200
        } catch (EntradaInvalidaException e) {
            return ResponseEntity.badRequest().build(); //400
        } catch (JogosNaoEncontradosException e) {
            return ResponseEntity.notFound().build(); //404
        } catch (AcessoAoBancoDeDadosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
        }
    }

    @GetMapping("dados/jogos/{time}/vitorias")
    public ResponseEntity<List<Jogo>> buscarVitoriasPorTime(@PathVariable("time") String time) {
        try {
            return ResponseEntity.ok(jogoService.buscarVitoriasPorTime(time)); //200
        } catch (EntradaInvalidaException e) {
            return ResponseEntity.badRequest().build(); //400
        } catch (JogosNaoEncontradosException e) {
            return ResponseEntity.notFound().build(); //404
        } catch (AcessoAoBancoDeDadosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
        }
    }

    @GetMapping("dados/jogos/{time}/empates")
    public ResponseEntity<List<Jogo>> buscarEmpatesPorTime(@PathVariable("time") String time) {
        try {
            return ResponseEntity.ok(jogoService.buscarEmpatesPorTime(time)); //200
        } catch (EntradaInvalidaException e) {
            return ResponseEntity.badRequest().build(); //400
        } catch (JogosNaoEncontradosException e) {
            return ResponseEntity.notFound().build(); //404
        } catch (AcessoAoBancoDeDadosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
        }
    }

    @GetMapping("dados/jogos/{time}/derrotas")
    public ResponseEntity<List<Jogo>> buscarDerrotasPorTime(@PathVariable("time") String time) {
        try {
            return ResponseEntity.ok(jogoService.buscarDerrotasPorTime(time)); //200
        } catch (EntradaInvalidaException e) {
            return ResponseEntity.badRequest().build(); //400
        } catch (JogosNaoEncontradosException e) {
            return ResponseEntity.notFound().build(); //404
        } catch (AcessoAoBancoDeDadosException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
        }
    }
}
