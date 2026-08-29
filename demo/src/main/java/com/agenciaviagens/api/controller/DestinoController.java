package com.agenciaviagens.api.controller;

import com.agenciaviagens.api.dto.AvaliacaoRequestDTO;
import com.agenciaviagens.api.dto.DestinoRequestDTO;
import com.agenciaviagens.api.dto.DestinoResponseDTO;
import com.agenciaviagens.api.service.DestinoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/destinos")
public class DestinoController {

    private final DestinoService destinoService;

    public DestinoController(DestinoService destinoService) {
        this.destinoService = destinoService;
    }

    @PostMapping
    public ResponseEntity<DestinoResponseDTO> cadastrar(@Valid @RequestBody DestinoRequestDTO request) {
        DestinoResponseDTO response = destinoService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<DestinoResponseDTO>> listar() {
        return ResponseEntity.ok(destinoService.listarTodos());
    }

    @GetMapping("/pesquisa")
    public ResponseEntity<List<DestinoResponseDTO>> pesquisar(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String local) {
        return ResponseEntity.ok(destinoService.pesquisar(nome, local));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinoResponseDTO> detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(destinoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DestinoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody DestinoRequestDTO request) {
        return ResponseEntity.ok(destinoService.atualizar(id, request));
    }

    @PatchMapping("/{id}/avaliacoes")
    public ResponseEntity<DestinoResponseDTO> avaliar(
            @PathVariable Long id,
            @Valid @RequestBody AvaliacaoRequestDTO request) {
        return ResponseEntity.ok(destinoService.avaliar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        destinoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}