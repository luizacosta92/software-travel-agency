package com.agenciaviagens.api.service.impl;

import com.agenciaviagens.api.dto.AvaliacaoRequestDTO;
import com.agenciaviagens.api.dto.DestinoRequestDTO;
import com.agenciaviagens.api.dto.DestinoResponseDTO;
import com.agenciaviagens.api.exception.DestinoNaoEncontradoException;
import com.agenciaviagens.api.model.Avaliacao;
import com.agenciaviagens.api.model.Destino;
import com.agenciaviagens.api.repository.DestinoRepository;
import com.agenciaviagens.api.service.DestinoService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DestinoServiceImpl implements DestinoService {

    private final DestinoRepository destinoRepository;

    public DestinoServiceImpl(DestinoRepository destinoRepository) {
        this.destinoRepository = destinoRepository;
    }

    @Override
    public DestinoResponseDTO cadastrar(DestinoRequestDTO request) {
        Destino destino = new Destino(null, request.getNome(), request.getLocalizacao(), request.getDescricao(), request.getAtividades(), List.of());
        Destino salvo = destinoRepository.save(destino);
        return toResponse(salvo);
    }

    @Override
    public List<DestinoResponseDTO> listarTodos() {
        return destinoRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public List<DestinoResponseDTO> pesquisar(String nome, String localizacao) {
        return destinoRepository.findByNomeOrLocalizacao(nome, localizacao).stream().map(this::toResponse).toList();
    }

    @Override
    public DestinoResponseDTO buscarPorId(Long id) {
        return toResponse(buscarDestino(id));
    }

    @Override
    public DestinoResponseDTO atualizar(Long id, DestinoRequestDTO request) {
        Destino destino = buscarDestino(id);
        destino.setNome(request.getNome());
        destino.setLocalizacao(request.getLocalizacao());
        destino.setDescricao(request.getDescricao());
        destino.setAtividades(request.getAtividades());

        return toResponse(destinoRepository.save(destino));
    }

    @Override
    public DestinoResponseDTO avaliar(Long id, AvaliacaoRequestDTO request) {
        Destino destino = buscarDestino(id);
        List<Avaliacao> avaliacoes = destino.getAvaliacoes();
        Avaliacao avaliacao = new Avaliacao((long) avaliacoes.size() + 1, request.getNota());
        avaliacoes.add(avaliacao);
        destino.setAvaliacoes(avaliacoes);

        return toResponse(destinoRepository.save(destino));
    }

    @Override
    public void excluir(Long id) {
        if (!destinoRepository.existsById(id)) {
            throw new DestinoNaoEncontradoException(id);
        }

        destinoRepository.deleteById(id);
    }

    private Destino buscarDestino(Long id) {
        return destinoRepository.findById(id).orElseThrow(() -> new DestinoNaoEncontradoException(id));
    }

    private DestinoResponseDTO toResponse(Destino destino) {
        List<Double> avaliacoes = destino.getAvaliacoes().stream().map(Avaliacao::getNota).toList();
        Double media = avaliacoes.isEmpty() ? 0.0 : avaliacoes.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

        return new DestinoResponseDTO(
                destino.getId(),
                destino.getNome(),
                destino.getLocalizacao(),
                destino.getDescricao(),
                List.copyOf(destino.getAtividades()),
                List.copyOf(avaliacoes),
                media);
    }
}