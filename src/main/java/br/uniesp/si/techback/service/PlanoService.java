package br.uniesp.si.techback.service;

import br.uniesp.si.techback.exception.EntidadeNaoEncontradaException;
import br.uniesp.si.techback.model.Plano;
import br.uniesp.si.techback.repository.PlanoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanoService {

    private final PlanoRepository planoRepository;

    public List<Plano> listar() {
        return planoRepository.findAll();
    }

    public Plano buscarPorId(Long id) {
        return planoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Plano não encontrado!"));
    }

    @Transactional
    public Plano salvar(Plano plano) {
        return planoRepository.save(plano);
    }

    @Transactional
    public Plano atualizar(Long id, Plano plano) {
        Plano existente = buscarPorId(id);
        existente.setNome(plano.getNome());
        existente.setValorMensal(plano.getValorMensal());
        return planoRepository.save(existente);
    }

    @Transactional
    public void excluir(Long id) {
        Plano plano = buscarPorId(id);
        planoRepository.delete(plano);
    }
}
