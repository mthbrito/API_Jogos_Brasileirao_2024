package com.example.API_Jogos_Brasileirao_2024.repository;

import com.example.API_Jogos_Brasileirao_2024.exception.TimeNaoEncontradoException;
import com.example.API_Jogos_Brasileirao_2024.model.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TimeRepository {

    private final JdbcTemplate jdbcTemplate;

    public TimeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> buscarTimes() {
        String sql = "SELECT DISTINCT(mandante) FROM jogos_brasileirao_2024 ORDER BY mandante";
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> rs.getString("mandante")
        );
    }

    public Time buscarDadosPorTime(String time) {
        Integer pontos = calcularPontos(time);
        Integer vitorias = calcularVitorias(time);
        Integer empates = calcularEmpates(time);
        Integer derrotas = calcularDerrotas(time);
        Integer golsPro = calcularGolsPro(time);
        Integer golsContra = calcularGolsContra(time);
        Integer saldoDeGols = calcularSaldoDeGols(time);
        if (pontos == null || vitorias == null || empates == null || derrotas == null || golsPro == null || golsContra == null || saldoDeGols == null) {
            return null;
        }
        return new Time(time, pontos, vitorias, empates, derrotas, golsPro, golsContra, saldoDeGols);
    }

    public List<Time> buscarDadosTimes() {
        List<String> times = buscarTimes();
        List<Time> dadosTimes = new ArrayList<>();
        times.forEach(time -> dadosTimes.add(buscarDadosPorTime(time)));
        return dadosTimes;
    }

    public Integer calcularPontos(String time) {
        String sql = "WITH " +
                "win_mandante as (SELECT COUNT(*)*3 FROM jogos_brasileirao_2024 WHERE mandante = ? AND gols_mandante > gols_visitante)," +
                "draw_mandante as (SELECT COUNT(*) FROM jogos_brasileirao_2024 WHERE mandante = ? AND gols_mandante = gols_visitante)," +
                "win_visitante as (SELECT COUNT(*)*3 FROM jogos_brasileirao_2024 WHERE visitante = ? AND gols_visitante > gols_mandante)," +
                "draw_visitante as (SELECT COUNT(*) FROM jogos_brasileirao_2024 WHERE visitante = ? AND gols_visitante = gols_mandante)" +
                "SELECT (SELECT * FROM win_mandante) + (SELECT * FROM draw_mandante) + (SELECT * FROM win_visitante) + (SELECT * FROM draw_visitante) as pontos";
        return jdbcTemplate.queryForObject(sql, Integer.class, time, time, time, time);
    }

    public Integer calcularVitorias(String time) {
        String sql = "SELECT COUNT(*) FROM jogos_brasileirao_2024 WHERE (mandante = ? AND gols_mandante > gols_visitante) OR (visitante = ? AND gols_visitante > gols_mandante)";
        return jdbcTemplate.queryForObject(sql, Integer.class, time, time);
    }

    public Integer calcularEmpates(String time) {
        String sql = "SELECT COUNT(*) FROM jogos_brasileirao_2024 WHERE (mandante = ? AND gols_mandante = gols_visitante) OR (visitante = ? AND gols_visitante = gols_mandante)";
        return jdbcTemplate.queryForObject(sql, Integer.class, time, time);
    }

    public Integer calcularDerrotas(String time) {
        String sql = "SELECT COUNT(*) FROM jogos_brasileirao_2024 WHERE (mandante = ? AND gols_mandante < gols_visitante) OR (visitante = ? AND gols_visitante < gols_mandante)";
        return jdbcTemplate.queryForObject(sql, Integer.class, time, time);
    }

    public Integer calcularGolsPro(String time) {
        String sql = "SELECT " +
                "(SELECT SUM(gols_mandante) FROM jogos_brasileirao_2024 WHERE mandante = ?) + " +
                "(SELECT SUM(gols_visitante) FROM jogos_brasileirao_2024 WHERE visitante = ?) as gols_pro";
        return jdbcTemplate.queryForObject(sql, Integer.class, time, time);
    }

    public Integer calcularGolsContra(String time) {
        String sql = "SELECT " +
                "(SELECT SUM(gols_visitante) FROM jogos_brasileirao_2024 WHERE mandante = ?) + " +
                "(SELECT SUM(gols_mandante) FROM jogos_brasileirao_2024 WHERE visitante = ?) as gols_contra";
        return jdbcTemplate.queryForObject(sql, Integer.class, time, time);
    }

    public Integer calcularSaldoDeGols(String time) {
        Integer golsPro = calcularGolsPro(time);
        Integer golsContra = calcularGolsContra(time);
        if (golsPro == null || golsContra == null) {
            return null;
        }
        return golsPro - golsContra;
    }
}
