package com.example.API_Jogos_Brasileirao_2024.repository;

import com.example.API_Jogos_Brasileirao_2024.exception.JogoNaoEncontradoException;
import com.example.API_Jogos_Brasileirao_2024.model.Jogo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JogoRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JogoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Jogo> buscarJogos() {
        String sql = "SELECT * FROM jogos_brasileirao_2024";
        return jdbcTemplate.query(
                sql,
                rowMapperJogo()
        );
    }

    public List<Jogo> buscarJogosPorRodada(String rodada) {
        int nRodada = Integer.parseInt(rodada);
        String sql = "SELECT * FROM jogos_brasileirao_2024 WHERE rodada = ?";
        return jdbcTemplate.query(
                sql,
                rowMapperJogo(),
                nRodada
        );
    }

    public List<Jogo> buscarJogosPorTime(String time) {
        if (isTimeInvalido(time)) {
            throw new JogoNaoEncontradoException(time);
        }
        String sql = "SELECT * FROM jogos_brasileirao_2024 WHERE mandante = ? OR visitante = ?";
        return jdbcTemplate.query(
                sql,
                rowMapperJogo(),
                time,
                time
        );
    }

    public List<Jogo> buscarJogosPorTimeMandante(String time) {
        if (isTimeInvalido(time)) {
            throw new JogoNaoEncontradoException(time);
        }
        String sql = "SELECT * FROM jogos_brasileirao_2024 WHERE mandante = ?";
        return jdbcTemplate.query(
                sql,
                rowMapperJogo(),
                time
        );
    }

    public List<Jogo> buscarJogosPorTimeVisitante(String time) {
        if (isTimeInvalido(time)) {
            throw new JogoNaoEncontradoException(time);
        }
        String sql = "SELECT * FROM jogos_brasileirao_2024 WHERE visitante = ?";
        return jdbcTemplate.query(
                sql,
                rowMapperJogo(),
                time
        );
    }

    public List<Jogo> buscarVitoriasPorTime(String time) {
        if (isTimeInvalido(time)) {
            throw new JogoNaoEncontradoException(time);
        }
        String sql = "SELECT * FROM jogos_brasileirao_2024 WHERE (mandante = ? and gols_mandante > gols_visitante) OR (visitante = ? and gols_visitante > gols_mandante)";
        return jdbcTemplate.query(
                sql,
                rowMapperJogo(),
                time,
                time
        );
    }

    public List<Jogo> buscarEmpatesPorTime(String time) {
        if (isTimeInvalido(time)) {
            throw new JogoNaoEncontradoException(time);
        }
        String sql = "SELECT * FROM jogos_brasileirao_2024 WHERE (mandante = ? and gols_mandante = gols_visitante) OR (visitante = ? and gols_visitante = gols_mandante)";
        return jdbcTemplate.query(
                sql,
                rowMapperJogo(),
                time,
                time
        );
    }

    public List<Jogo> buscarDerrotasPorTime(String time) throws DataAccessException {
        if (isTimeInvalido(time)) {
            throw new JogoNaoEncontradoException(time);
        }
        String sql = "SELECT * FROM jogos_brasileirao_2024 WHERE (mandante = ? and gols_mandante < gols_visitante) OR (visitante = ? and gols_visitante < gols_mandante)";
        return jdbcTemplate.query(
                sql,
                rowMapperJogo(),
                time,
                time
        );
    }

    private RowMapper<Jogo> rowMapperJogo() {
        return (rs, rowNum) -> new Jogo(
                rs.getInt("id"),
                rs.getInt("rodada"),
                rs.getString("mandante"),
                rs.getInt("gols_mandante"),
                rs.getString("visitante"),
                rs.getInt("gols_visitante")
        );
    }

    private boolean isTimeInvalido(String time) throws DataAccessException {
        String sql = "SELECT COUNT(*) = 0 FROM jogos_brasileirao_2024 WHERE mandante = ? OR visitante = ?";
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, time, time));
    }
}
