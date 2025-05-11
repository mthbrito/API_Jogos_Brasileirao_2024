package com.example.API_Jogos_Brasileirao_2024.repository;

import com.example.API_Jogos_Brasileirao_2024.model.Time;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public List<Map<String, Object>> calcularClassificacao() {
        String sql = "WITH " +
                "times AS (SELECT DISTINCT(mandante) AS time FROM jogos_brasileirao_2024)," +
                "vitorias_mandante AS (SELECT mandante AS time, COUNT(*) AS vitorias_mandante FROM jogos_brasileirao_2024 WHERE gols_mandante > gols_visitante  GROUP BY mandante)," +
                "vitorias_visitante AS (SELECT visitante AS time, COUNT(*) AS vitorias_visitante FROM jogos_brasileirao_2024 WHERE gols_visitante > gols_mandante  GROUP BY visitante)," +
                "empates_mandante AS (SELECT mandante AS time, COUNT(*) AS empates_mandante FROM jogos_brasileirao_2024 WHERE gols_mandante = gols_visitante GROUP BY mandante)," +
                "empates_visitante AS (SELECT visitante AS time, COUNT(*) AS empates_visitante FROM jogos_brasileirao_2024 WHERE gols_visitante = gols_mandante  GROUP BY visitante)," +
                "derrotas_mandante AS (SELECT mandante AS time, COUNT(*) AS derrotas_mandante FROM jogos_brasileirao_2024 WHERE gols_mandante < gols_visitante GROUP BY mandante)," +
                "derrotas_visitante AS (SELECT visitante AS time, COUNT(*) AS derrotas_visitante FROM jogos_brasileirao_2024 WHERE gols_visitante < gols_mandante GROUP BY visitante)," +
                "gols_pro_mandante AS (SELECT mandante AS time, SUM(gols_mandante) AS gols FROM jogos_brasileirao_2024 GROUP BY mandante)," +
                "gols_pro_visitante AS (SELECT visitante AS time, SUM(gols_visitante) AS gols FROM jogos_brasileirao_2024 GROUP BY visitante)," +
                "gols_contra_mandante AS (SELECT mandante AS time, SUM(gols_visitante) AS gols FROM jogos_brasileirao_2024 GROUP BY mandante)," +
                "gols_contra_visitante AS (SELECT visitante AS time, SUM(gols_mandante) AS gols FROM jogos_brasileirao_2024 GROUP BY visitante)," +
                "tabela AS" +
                "(SELECT t.time AS time, COALESCE(vm.vitorias_mandante, 0) + COALESCE(vv.vitorias_visitante, 0) AS vitorias, COALESCE(em.empates_mandante, 0) + COALESCE(ev.empates_visitante, 0) AS empates, COALESCE(dm.derrotas_mandante, 0) + COALESCE(dv.derrotas_visitante, 0) AS derrotas, COALESCE(gpm.gols, 0) + COALESCE(gpv.gols, 0) AS gols_pro, COALESCE(gcm.gols, 0) + COALESCE(gcv.gols, 0) AS gols_contra FROM times t " +
                "LEFT JOIN vitorias_mandante vm ON t.time=vm.time " +
                "LEFT JOIN vitorias_visitante vv ON t.time=vv.time " +
                "LEFT JOIN empates_mandante em ON t.time=em.time " +
                "LEFT JOIN empates_visitante ev ON t.time=ev.time " +
                "LEFT JOIN derrotas_mandante dm ON t.time=dm.time " +
                "LEFT JOIN derrotas_visitante dv ON t.time=dv.time " +
                "LEFT JOIN gols_pro_mandante gpm ON t.time=gpm.time " +
                "LEFT JOIN gols_pro_visitante gpv ON t.time=gpv.time " +
                "LEFT JOIN gols_contra_mandante gcm ON t.time=gcm.time " +
                "LEFT JOIN gols_contra_visitante gcv ON t.time=gcv.time), " +
                "classificacao AS (SELECT RANK() OVER (ORDER BY (vitorias * 3 + empates) DESC, vitorias DESC, (gols_pro - gols_contra) DESC, gols_pro DESC) AS posicao, time, (vitorias * 3 + empates) AS pontos, vitorias, empates, derrotas, gols_pro, gols_contra, gols_pro - gols_contra AS saldo_gols FROM tabela)" +
                "SELECT * FROM classificacao";
        return jdbcTemplate.queryForList(sql);
    }

    public Integer calcularPontos(String time) {
        String sql = "WITH " +
                "vitorias_mandante as (SELECT COUNT(*)*3 FROM jogos_brasileirao_2024 WHERE mandante = ? AND gols_mandante > gols_visitante)," +
                "empates_mandante as (SELECT COUNT(*) FROM jogos_brasileirao_2024 WHERE mandante = ? AND gols_mandante = gols_visitante)," +
                "vitorias_visitante as (SELECT COUNT(*)*3 FROM jogos_brasileirao_2024 WHERE visitante = ? AND gols_visitante > gols_mandante)," +
                "empates_visitante as (SELECT COUNT(*) FROM jogos_brasileirao_2024 WHERE visitante = ? AND gols_visitante = gols_mandante)" +
                "SELECT (SELECT * FROM vitorias_mandante) + (SELECT * FROM empates_mandante) + (SELECT * FROM vitorias_visitante) + (SELECT * FROM empates_visitante) as pontos";
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
