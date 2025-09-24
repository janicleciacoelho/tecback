package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.model.Favorito;
import br.uniesp.si.techback.service.FavoritoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favoritos")
@RequiredArgsConstructor
public class FavoritoController {

    private final FavoritoService favoritoService;

    @GetMapping
    public List<Favorito> listar() {
        return favoritoService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Favorito> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(favoritoService.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Favorito> criar(@Valid @RequestBody Favorito favorito) {
        return ResponseEntity.ok(favoritoService.salvar(favorito));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            favoritoService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
