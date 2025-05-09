package com.example.API_Jogos_Brasileirao_2024.model;

import org.springframework.stereotype.Component;

@Component
public class Time {

    private String nome;
    private int pontos;
    private int vitorias;
    private int empates;
    private int derrotas;
    private int golsPro;
    private int golsContra;
    private int saldoDeGols;

    public Time() {
    }

    public Time(String nome, int pontos, int vitorias, int empates, int derrotas, int golsPro, int golsContra, int saldoDeGols) {
        this.nome = nome;
        this.pontos = pontos;
        this.vitorias = vitorias;
        this.empates = empates;
        this.derrotas = derrotas;
        this.golsPro = golsPro;
        this.golsContra = golsContra;
        this.saldoDeGols = saldoDeGols;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public int getGolsPro() {
        return golsPro;
    }

    public void setGolsPro(int golsPro) {
        this.golsPro = golsPro;
    }

    public int getGolsContra() {
        return golsContra;
    }

    public void setGolsContra(int golsContra) {
        this.golsContra = golsContra;
    }

    public int getSaldoDeGols() {
        return saldoDeGols;
    }

    public void setSaldoDeGols(int saldoDeGols) {
        this.saldoDeGols = saldoDeGols;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\n" +
                "Pontos: " + pontos + "\n" +
                "Vitórias: " + vitorias + "\n" +
                "Empates: " + empates + "\n" +
                "Derrotas: " + derrotas + "\n" +
                "Gols pro: " + golsPro + "\n" +
                "Gols contra: " + golsContra + "\n" +
                "Saldo de gols: " + saldoDeGols + "\n";
    }
}
