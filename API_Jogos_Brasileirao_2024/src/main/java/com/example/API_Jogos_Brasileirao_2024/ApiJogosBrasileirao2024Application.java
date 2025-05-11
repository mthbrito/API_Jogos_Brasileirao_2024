package com.example.API_Jogos_Brasileirao_2024;

import com.example.API_Jogos_Brasileirao_2024.controller.TimeController;
import com.example.API_Jogos_Brasileirao_2024.model.Jogo;
import com.example.API_Jogos_Brasileirao_2024.repository.JogoRepository;
import com.example.API_Jogos_Brasileirao_2024.model.Time;
import com.example.API_Jogos_Brasileirao_2024.repository.TimeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Comparator;
import java.util.List;

@SpringBootApplication
public class ApiJogosBrasileirao2024Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ApiJogosBrasileirao2024Application.class, args);

        TimeController timeController = context.getBean(TimeController.class);
        JogoRepository repoJ = context.getBean(JogoRepository.class);
        TimeRepository repoT = context.getBean(TimeRepository.class);
//		List<Jogo> jogos = repository.buscarJogosPorTime("Vitória");
//		List<Jogo> jogos = repository.buscarJogos();
//		jogos.forEach(System.out::print);
//		List<String> times = repoT.buscarTimes();
//		times.forEach(System.out::println);
        int pontos = repoT.calcularPontos("Palmeiras");
        int vitorias = repoT.calcularVitorias("Palmeiras");
        int derrotas = repoT.calcularDerrotas("Cuiabá");
        int empates = repoT.calcularEmpates("Bahia");
        int golsPro = repoT.calcularGolsPro("Fortaleza");
        int golsContra = repoT.calcularGolsContra("Fortaleza");
        int saldoDeGols = repoT.calcularSaldoDeGols("São Paulo");
        Time time = repoT.buscarDadosPorTime("Atlético-MG");
        List<Time> times = repoT.buscarDadosTimes();
        List<Jogo> jogos = repoJ.buscarJogosPorTime("Atlético-MG");
//		System.out.println(jogos);
//		System.out.println(time);
//        times.sort(Comparator.comparing(Time::calcularPontos).reversed());
//		times.forEach(equipe -> {
//			System.out.println(equipe.calcularNome() + " | " + equipe.calcularPontos());
//		});
//        System.out.println(timeController.calcularDadosTime("Santos"));
//        System.out.println(repoJ.buscarJogosPorRodada(1));
//        System.out.println(repoT.buscarDadosPorTime("Santos"));

        System.out.println(repoT.calcularGolsPro("Santos"));
        System.out.println(repoT.calcularSaldoDeGols("Santos"));
        System.out.println(repoT.buscarDadosPorTime("Santos"));
        System.out.println(repoT.calcularClassificacao());
    }

}
