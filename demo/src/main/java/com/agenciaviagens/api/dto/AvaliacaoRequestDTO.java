package com.agenciaviagens.api.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class AvaliacaoRequestDTO {

    @NotNull(message = "A nota é obrigatória")
    @DecimalMin(value = "0.0", message = "A nota deve ser maior ou igual a 0")
    @DecimalMax(value = "5.0", message = "A nota deve ser menor ou igual a 5")
    private Double nota;

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
}