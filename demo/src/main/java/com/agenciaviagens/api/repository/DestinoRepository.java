package com.agenciaviagens.api.repository;

import com.agenciaviagens.api.model.Destino;
import java.util.List;
import java.util.Optional;

public interface DestinoRepository {

    Destino save(Destino destino);

    List<Destino> findAll();

    Optional<Destino> findById(Long id);

    List<Destino> findByNomeOrLocalizacao(String nome, String localizacao);

    void deleteById(Long id);

    boolean existsById(Long id);
}