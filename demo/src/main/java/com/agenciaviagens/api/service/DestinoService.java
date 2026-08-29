package com.agenciaviagens.api.service;

import com.agenciaviagens.api.dto.AvaliacaoRequestDTO;
import com.agenciaviagens.api.dto.DestinoRequestDTO;
import com.agenciaviagens.api.dto.DestinoResponseDTO;
import java.util.List;

public interface DestinoService {

    DestinoResponseDTO cadastrar(DestinoRequestDTO request);

    List<DestinoResponseDTO> listarTodos();

    List<DestinoResponseDTO> pesquisar(String nome, String localizacao);

    DestinoResponseDTO buscarPorId(Long id);

    DestinoResponseDTO atualizar(Long id, DestinoRequestDTO request);

    DestinoResponseDTO avaliar(Long id, AvaliacaoRequestDTO request);

    void excluir(Long id);
}