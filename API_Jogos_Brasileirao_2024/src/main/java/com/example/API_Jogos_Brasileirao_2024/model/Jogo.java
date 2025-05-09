package com.example.API_Jogos_Brasileirao_2024.model;

import org.springframework.stereotype.Component;

@Component
public class Jogo {

    private int id;
    private int rodada;
    private String mandante;
    private int gols_mandante;
    private String visitante;
    private int gols_visitante;

    public Jogo() {
    }

    public Jogo(int id, int rodada, String mandante, int gols_mandante, String visitante, int gols_visitante) {
        this.id = id;
        this.rodada = rodada;
        this.mandante = mandante;
        this.gols_mandante = gols_mandante;
        this.visitante = visitante;
        this.gols_visitante = gols_visitante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRodada() {
        return rodada;
    }

    public void setRodada(int rodada) {
        this.rodada = rodada;
    }

    public String getMandante() {
        return mandante;
    }

    public void setMandante(String mandante) {
        this.mandante = mandante;
    }

    public int getGols_mandante() {
        return gols_mandante;
    }

    public void setGols_mandante(int gols_mandante) {
        this.gols_mandante = gols_mandante;
    }

    public String getVisitante() {
        return visitante;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    public int getGols_visitante() {
        return gols_visitante;
    }

    public void setGols_visitante(int gols_visitante) {
        this.gols_visitante = gols_visitante;
    }

    @Override
    public String toString() {
        return rodada + "ª rodada: " +
                mandante + " " + gols_mandante + " x " +
                gols_visitante + " " + visitante + "\n"
                ;
    }
}
