package com.example.API_Jogos_Brasileirao_2024.repository;

import com.example.API_Jogos_Brasileirao_2024.model.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TimeRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
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
        return new Time(time, getPontos(time), getVitorias(time), getEmpates(time), getDerrotas(time), getGolsPro(time), getGolsContra(time), getSaldoDeGols(time));
    }

    public List<Time> buscarDadosTimes() {
        List<String> times = buscarTimes();
        List<Time> dadosTimes = new ArrayList<>();
        times.forEach(time -> dadosTimes.add(buscarDadosPorTime(time)));
        return dadosTimes;
    }

    public Integer getPontos(String time) {
        String sql = "WITH " +
                "win_mandante as (SELECT COUNT(*)*3 FROM jogos_brasileirao_2024 WHERE mandante = ? AND gols_mandante > gols_visitante)," +
                "draw_mandante as (SELECT COUNT(*) FROM jogos_brasileirao_2024 WHERE mandante = ? AND gols_mandante = gols_visitante)," +
                "win_visitante as (SELECT COUNT(*)*3 FROM jogos_brasileirao_2024 WHERE visitante = ? AND gols_visitante > gols_mandante)," +
                "draw_visitante as (SELECT COUNT(*) FROM jogos_brasileirao_2024 WHERE visitante = ? AND gols_visitante = gols_mandante)" +
                "SELECT (SELECT * FROM win_mandante) + (SELECT * FROM draw_mandante) + (SELECT * FROM win_visitante) + (SELECT * FROM draw_visitante) as pontos";
        return jdbcTemplate.queryForObject(sql, Integer.class, time, time, time, time);
    }

    public Integer getVitorias(String time) {
        String sql = "SELECT COUNT(*) FROM jogos_brasileirao_2024 WHERE (mandante = ? AND gols_mandante > gols_visitante) OR (visitante = ? AND gols_visitante > gols_mandante)";
        return jdbcTemplate.queryForObject(sql, Integer.class, time, time);
    }

    public Integer getEmpates(String time) {
        String sql = "SELECT COUNT(*) FROM jogos_brasileirao_2024 WHERE (mandante = ? AND gols_mandante = gols_visitante) OR (visitante = ? AND gols_visitante = gols_mandante)";
        return jdbcTemplate.queryForObject(sql, Integer.class, time, time);
    }

    public Integer getDerrotas(String time) {
        String sql = "SELECT COUNT(*) FROM jogos_brasileirao_2024 WHERE (mandante = ? AND gols_mandante < gols_visitante) OR (visitante = ? AND gols_visitante < gols_mandante)";
        return jdbcTemplate.queryForObject(sql, Integer.class, time, time);
    }

    public Integer getGolsPro(String time) {
        String sql = "SELECT " +
                "(SELECT SUM(gols_mandante) FROM jogos_brasileirao_2024 WHERE mandante = ?) + " +
                "(SELECT SUM(gols_visitante) FROM jogos_brasileirao_2024 WHERE visitante = ?) as gols_pro";
        return jdbcTemplate.queryForObject(sql, Integer.class, time, time);
    }

    public Integer getGolsContra(String time) {
        String sql = "SELECT " +
                "(SELECT SUM(gols_visitante) FROM jogos_brasileirao_2024 WHERE mandante = ?) + " +
                "(SELECT SUM(gols_mandante) FROM jogos_brasileirao_2024 WHERE visitante = ?) as gols_contra";
        return jdbcTemplate.queryForObject(sql, Integer.class, time, time);
    }

    public Integer getSaldoDeGols(String time) {
        return getGolsPro(time) - getGolsContra(time);
    }
}
