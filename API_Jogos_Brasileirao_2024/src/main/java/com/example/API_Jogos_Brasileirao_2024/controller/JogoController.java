package com.example.API_Jogos_Brasileirao_2024.controller;

import com.example.API_Jogos_Brasileirao_2024.model.Jogo;
import com.example.API_Jogos_Brasileirao_2024.repository.JogoRepository;
import com.example.API_Jogos_Brasileirao_2024.service.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Jogo> getDadosJogos() {
        return jogoService.buscarJogos();
    }

    @GetMapping("dados/jogos/rodada/{rodada}")
    public List<Jogo> getDadosJogosPorRodada(@PathVariable("rodada") int rodada) {
        return jogoService.buscarJogosPorRodada(rodada);
    }

    @GetMapping("dados/jogos/{time}")
    public List<Jogo> getDadosJogosPorTime(@PathVariable("time") String time) {
        return jogoService.buscarJogosPorTime(time);
    }

    @GetMapping("dados/jogos/{time}/mandante")
    public List<Jogo> getDadosJogosPorTimeMandante(@PathVariable("time") String time) {
        return jogoService.buscarJogosPorTimeMandante(time);
    }

    @GetMapping("dados/jogos/{time}/visitante")
    public List<Jogo> getDadosJogosPorTimeVisitante(@PathVariable("time") String time) {
        return jogoService.buscarJogosPorTimeVisitante(time);
    }

    @GetMapping("dados/jogos/{time}/vitorias")
    public List<Jogo> getDadosVitoriasTime(@PathVariable("time") String time) {
        return jogoService.buscarVitoriasPorTime(time);
    }

    @GetMapping("dados/jogos/{time}/empates")
    public List<Jogo> getDadosEmpatesTime(@PathVariable("time") String time) {
        return jogoService.buscarEmpatesPorTime(time);
    }

    @GetMapping("dados/jogos/{time}/derrotas")
    public List<Jogo> getDadosDerrotasTime(@PathVariable("time") String time) {
        return jogoService.buscarDerrotasPorTime(time);
    }
}
