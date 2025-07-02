package com.example.todoapp.model;

import java.time.LocalDateTime;

public class Tarefa {
    private String titulo;
    private LocalDateTime dataInicial;
    private LocalDateTime dataDeConclusao;
    private boolean repeticao;
    private boolean importante;
    private int tempoRepeticao;

    public Tarefa(){
    }

    public Tarefa(String titulo, LocalDateTime dataInicial) {
        this.titulo = titulo;
        this.dataInicial = dataInicial;
    }

    public Tarefa(String titulo, LocalDateTime dataInicial, LocalDateTime dataDeConclusao, boolean repeticao, boolean importante, int tempoRepeticao) {
        this.titulo = titulo;
        this.dataInicial = dataInicial;
        this.dataDeConclusao = dataDeConclusao;
        this.repeticao = repeticao;
        this.importante = importante;
        this.tempoRepeticao = tempoRepeticao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDateTime getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDateTime dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDateTime getDataDeConclusao() {
        return dataDeConclusao;
    }

    public void setDataDeConclusao(LocalDateTime dataDeConclusao) {
        this.dataDeConclusao = dataDeConclusao;
    }

    public boolean isRepeticao() {
        return repeticao;
    }

    public void setRepeticao(boolean repeticao) {
        this.repeticao = repeticao;
    }

    public boolean isImportante() {
        return importante;
    }

    public void setImportante(boolean importante) {
        this.importante = importante;
    }

    public int getTempoRepeticao() {
        return tempoRepeticao;
    }

    public void setTempoRepeticao(int tempoRepeticao) {
        this.tempoRepeticao = tempoRepeticao;
    }
}
