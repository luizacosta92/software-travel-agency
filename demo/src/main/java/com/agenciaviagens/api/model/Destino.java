package com.agenciaviagens.api.model;

import java.util.ArrayList;
import java.util.List;

public class Destino {

    private Long id;
    private String nome;
    private String localizacao;
    private String descricao;
    private List<String> atividades = new ArrayList<>();
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    public Destino() {
    }

    public Destino(Long id, String nome, String localizacao, String descricao, List<String> atividades, List<Avaliacao> avaliacoes) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.descricao = descricao;
        setAtividades(atividades);
        setAvaliacoes(avaliacoes);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<String> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<String> atividades) {
        this.atividades = atividades == null ? new ArrayList<>() : new ArrayList<>(atividades);
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes == null ? new ArrayList<>() : new ArrayList<>(avaliacoes);
    }
}