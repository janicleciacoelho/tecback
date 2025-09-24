package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.model.Plano;
import br.uniesp.si.techback.service.PlanoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planos")
@RequiredArgsConstructor
public class PlanoController {

    private final PlanoService planoService;

    @GetMapping
    public List<Plano> listar() {
        return planoService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plano> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(planoService.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Plano> criar(@Valid @RequestBody Plano plano) {
        return ResponseEntity.ok(planoService.salvar(plano));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plano> atualizar(@PathVariable Long id, @Valid @RequestBody Plano plano) {
        try {
            return ResponseEntity.ok(planoService.atualizar(id, plano));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            planoService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
