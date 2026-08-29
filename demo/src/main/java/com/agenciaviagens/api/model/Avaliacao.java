package com.agenciaviagens.api.model;

public class Avaliacao {

    private Long id;
    private Double nota;

    public Avaliacao() {
    }

    public Avaliacao(Long id, Double nota) {
        this.id = id;
        this.nota = nota;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
}