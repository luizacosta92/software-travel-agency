package com.agenciaviagens.api.exception;

public class DestinoNaoEncontradoException extends RuntimeException {

    public DestinoNaoEncontradoException(Long id) {
        super("Destino não encontrado para o id " + id);
    }
}