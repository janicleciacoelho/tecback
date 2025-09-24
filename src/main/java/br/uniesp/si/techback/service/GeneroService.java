package br.uniesp.si.techback.service;

import br.uniesp.si.techback.exception.EntidadeNaoEncontradaException;
import br.uniesp.si.techback.model.Genero;
import br.uniesp.si.techback.repository.GeneroRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneroService {

    private final GeneroRepository generoRepository;

    public List<Genero> listar() {
        return generoRepository.findAll();
    }

    public Genero buscarPorId(Long id) {
        return generoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Gênero não encontrado!"));
    }

    @Transactional
    public Genero salvar(Genero genero) {
        return generoRepository.save(genero);
    }

    @Transactional
    public Genero atualizar(Long id, Genero genero) {
        Genero existente = buscarPorId(id);
        existente.setNome(genero.getNome());
        return generoRepository.save(existente);
    }

    @Transactional
    public void excluir(Long id) {
        Genero genero = buscarPorId(id);
        generoRepository.delete(genero);
    }
}
