package com.example.todoapp.model.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tarefa {
    private long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataInicial;
    private LocalDateTime dataDeConclusao;
    private boolean importante;
    private boolean concluida;

    public Tarefa(long id, String titulo, String descricao, LocalDateTime dataDeConclusao, boolean importante, boolean concluida) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicial = LocalDateTime.now();
        this.dataDeConclusao = dataDeConclusao;
        this.importante = importante;
        this.concluida = concluida;
    }

    public Tarefa(String titulo, String descricao, LocalDateTime dataDeConclusao, boolean importante, boolean concluida) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicial = LocalDateTime.now();
        this.dataDeConclusao = dataDeConclusao;
        this.importante = importante;
        this.concluida = concluida;
    }

    public long getid() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public boolean isImportante() {
        return importante;
    }

    public void setImportante(boolean importante) {
        this.importante = importante;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public String getDataInicialFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dataInicial.format(formatter);
    }

    public String getDataDeConclusaoFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dataDeConclusao.format(formatter);
    }
}
