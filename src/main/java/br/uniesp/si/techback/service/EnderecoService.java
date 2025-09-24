package br.uniesp.si.techback.service;

import br.uniesp.si.techback.exception.EntidadeNaoEncontradaException;
import br.uniesp.si.techback.model.Endereco;
import br.uniesp.si.techback.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public List<Endereco> listar() {
        return enderecoRepository.findAll();
    }

    public Endereco buscarPorId(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Endereço não encontrado!"));
    }

    @Transactional
    public Endereco salvar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    @Transactional
    public Endereco atualizar(Long id, Endereco endereco) {
        Endereco existente = buscarPorId(id);
        existente.setRua(endereco.getRua());
        existente.setCidade(endereco.getCidade());
        existente.setEstado(endereco.getEstado());
        existente.setCep(endereco.getCep());
        existente.setUsuario(endereco.getUsuario());
        return enderecoRepository.save(existente);
    }

    @Transactional
    public void excluir(Long id) {
        Endereco endereco = buscarPorId(id);
        enderecoRepository.delete(endereco);
    }
}
