package br.uniesp.si.techback.service;

import br.uniesp.si.techback.exception.EntidadeNaoEncontradaException;
import br.uniesp.si.techback.model.Favorito;
import br.uniesp.si.techback.repository.FavoritoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoritoService {

    private final FavoritoRepository favoritoRepository;

    public List<Favorito> listar() {
        return favoritoRepository.findAll();
    }

    public Favorito buscarPorId(Long id) {
        return favoritoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Favorito não encontrado!"));
    }

    @Transactional
    public Favorito salvar(Favorito favorito) {
        return favoritoRepository.save(favorito);
    }

    @Transactional
    public void excluir(Long id) {
        Favorito favorito = buscarPorId(id);
        favoritoRepository.delete(favorito);
    }
}
