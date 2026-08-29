package com.agenciaviagens.api.dto;

import java.util.List;

public record DestinoResponseDTO(
        Long id,
        String nome,
        String localizacao,
        String descricao,
        List<String> atividades,
        List<Double> avaliacoes,
        Double mediaAvaliacoes) {
}