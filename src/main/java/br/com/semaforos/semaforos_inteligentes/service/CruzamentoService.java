package br.com.semaforos.semaforos_inteligentes.service;

import br.com.semaforos.semaforos_inteligentes.model.Cruzamento;
import br.com.semaforos.semaforos_inteligentes.repository.CruzamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CruzamentoService {

    private final CruzamentoRepository repository;

    public CruzamentoService(CruzamentoRepository repository) {
        this.repository = repository;
    }

    public Cruzamento salvar(Cruzamento cruzamento) {
        return repository.save(cruzamento);
    }

    public List<Cruzamento> listarTodos() {
        return repository.findAll();
    }

    public Optional<Cruzamento> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Optional<Cruzamento> atualizar(Long id, Cruzamento cruzamentoAtualizado) {
        Optional<Cruzamento> existente = repository.findById(id);

        if (existente.isPresent()) {
            Cruzamento cruzamento = existente.get();
            cruzamento.setNome(cruzamentoAtualizado.getNome());
            cruzamento.setLocalizacao(cruzamentoAtualizado.getLocalizacao());
            return Optional.of(repository.save(cruzamento));
        }

        return Optional.empty();
    }


    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
