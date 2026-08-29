package com.agenciaviagens.api.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.agenciaviagens.api.dto.AvaliacaoRequestDTO;
import com.agenciaviagens.api.dto.DestinoRequestDTO;
import com.agenciaviagens.api.dto.DestinoResponseDTO;
import com.agenciaviagens.api.repository.DestinoRepositoryEmMemoria;
import com.agenciaviagens.api.service.impl.DestinoServiceImpl;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DestinoServiceImplTest {

    private DestinoService destinoService;

    @BeforeEach
    void setUp() {
        destinoService = new DestinoServiceImpl(new DestinoRepositoryEmMemoria());
    }

    @Test
    void deveCadastrarEAtribuirIdAoDestino() {
        DestinoRequestDTO request = novoRequest();

        DestinoResponseDTO response = destinoService.cadastrar(request);

        assertThat(response.id()).isNotNull();
        assertThat(response.nome()).isEqualTo("Rio de Janeiro");
        assertThat(response.mediaAvaliacoes()).isZero();
    }

    @Test
    void deveRecalcularMediaAoAvaliarDestino() {
        DestinoResponseDTO cadastrado = destinoService.cadastrar(novoRequest());

        AvaliacaoRequestDTO primeiraAvaliacao = new AvaliacaoRequestDTO();
        primeiraAvaliacao.setNota(4.0);
        destinoService.avaliar(cadastrado.id(), primeiraAvaliacao);

        AvaliacaoRequestDTO segundaAvaliacao = new AvaliacaoRequestDTO();
        segundaAvaliacao.setNota(5.0);
        DestinoResponseDTO atualizado = destinoService.avaliar(cadastrado.id(), segundaAvaliacao);

        assertThat(atualizado.avaliacoes()).containsExactly(4.0, 5.0);
        assertThat(atualizado.mediaAvaliacoes()).isEqualTo(4.5);
    }

    @Test
    void devePesquisarPorNomeOuLocalizacao() {
        destinoService.cadastrar(novoRequest());

        DestinoRequestDTO outroDestino = new DestinoRequestDTO();
        outroDestino.setNome("Buenos Aires");
        outroDestino.setLocalizacao("Argentina");
        outroDestino.setDescricao("Cidade cultural");
        outroDestino.setAtividades(List.of("Passeio"));
        destinoService.cadastrar(outroDestino);

        assertThat(destinoService.pesquisar("rio", null)).hasSize(1);
        assertThat(destinoService.pesquisar(null, "argentina")).hasSize(1);
    }

    private DestinoRequestDTO novoRequest() {
        DestinoRequestDTO request = new DestinoRequestDTO();
        request.setNome("Rio de Janeiro");
        request.setLocalizacao("Brasil");
        request.setDescricao("Destino turístico com praias e pontos históricos");
        request.setAtividades(List.of("Cristo Redentor", "Pão de Açúcar"));
        return request;
    }
}