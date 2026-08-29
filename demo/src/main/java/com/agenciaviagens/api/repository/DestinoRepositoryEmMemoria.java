package com.agenciaviagens.api.repository;

import com.agenciaviagens.api.model.Avaliacao;
import com.agenciaviagens.api.model.Destino;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class DestinoRepositoryEmMemoria implements DestinoRepository {

    private final Map<Long, Destino> armazenamentos = new LinkedHashMap<>();
    private final AtomicLong sequencia = new AtomicLong(0);

    @Override
    public synchronized Destino save(Destino destino) {
        Long id = destino.getId();
        if (id == null) {
            id = sequencia.incrementAndGet();
        }

        Destino salvo = copiar(destino);
        salvo.setId(id);
        armazenamentos.put(id, salvo);
        return copiar(salvo);
    }

    @Override
    public List<Destino> findAll() {
        return armazenamentos.values().stream().map(this::copiar).toList();
    }

    @Override
    public Optional<Destino> findById(Long id) {
        return Optional.ofNullable(armazenamentos.get(id)).map(this::copiar);
    }

    @Override
    public List<Destino> findByNomeOrLocalizacao(String nome, String localizacao) {
        String nomeNormalizado = normalizar(nome);
        String localizacaoNormalizada = normalizar(localizacao);

        if (nomeNormalizado.isEmpty() && localizacaoNormalizada.isEmpty()) {
            return findAll();
        }

        return armazenamentos.values().stream()
                .filter(destino -> contem(destino.getNome(), nomeNormalizado) || contem(destino.getLocalizacao(), localizacaoNormalizada))
                .map(this::copiar)
                .toList();
    }

    @Override
    public synchronized void deleteById(Long id) {
        armazenamentos.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return armazenamentos.containsKey(id);
    }

    private boolean contem(String valor, String termo) {
        if (termo.isEmpty()) {
            return false;
        }

        return normalizar(valor).contains(termo);
    }

    private String normalizar(String valor) {
        return valor == null ? "" : valor.trim().toLowerCase(Locale.ROOT);
    }

    private Destino copiar(Destino destino) {
        List<String> atividades = destino.getAtividades() == null ? new ArrayList<>() : new ArrayList<>(destino.getAtividades());
        List<Avaliacao> avaliacoes = destino.getAvaliacoes() == null ? new ArrayList<>() : destino.getAvaliacoes().stream()
                .map(avaliacao -> new Avaliacao(avaliacao.getId(), avaliacao.getNota()))
                .toList();

        return new Destino(destino.getId(), destino.getNome(), destino.getLocalizacao(), destino.getDescricao(), atividades, avaliacoes);
    }
}