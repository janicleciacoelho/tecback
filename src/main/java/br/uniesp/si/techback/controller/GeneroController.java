package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.model.Genero;
import br.uniesp.si.techback.service.GeneroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/generos")
@RequiredArgsConstructor
public class GeneroController {

    private final GeneroService generoService;

    @GetMapping
    public List<Genero> listar() {
        return generoService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genero> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(generoService.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Genero> criar(@Valid @RequestBody Genero genero) {
        return ResponseEntity.ok(generoService.salvar(genero));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genero> atualizar(@PathVariable Long id, @Valid @RequestBody Genero genero) {
        try {
            return ResponseEntity.ok(generoService.atualizar(id, genero));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            generoService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
