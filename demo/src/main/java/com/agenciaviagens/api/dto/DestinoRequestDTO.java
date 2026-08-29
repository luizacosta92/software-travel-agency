package com.agenciaviagens.api.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class DestinoRequestDTO {

    @NotBlank(message = "O nome do destino é obrigatório")
    private String nome;

    @NotBlank(message = "A localização do destino é obrigatória")
    private String localizacao;

    @NotBlank(message = "A descrição do destino é obrigatória")
    private String descricao;

    private List<String> atividades = new ArrayList<>();

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
}