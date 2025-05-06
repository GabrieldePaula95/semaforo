package br.com.semaforos.semaforos_inteligentes.service;

import br.com.semaforos.semaforos_inteligentes.model.StatusSemaforo;
import br.com.semaforos.semaforos_inteligentes.repository.StatusSemaforoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusSemaforoService {

    private final StatusSemaforoRepository repository;

    public StatusSemaforoService(StatusSemaforoRepository repository) {
        this.repository = repository;
    }

    public List<StatusSemaforo> listarTodos() {
        return repository.findAll();
    }

    public Optional<StatusSemaforo> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public StatusSemaforo salvar(StatusSemaforo statusSemaforo) {
        return repository.save(statusSemaforo);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Optional<StatusSemaforo> atualizar(Long id, StatusSemaforo atualizado) {
        return repository.findById(id).map(statusExistente -> {
            statusExistente.setDescricao(atualizado.getDescricao());
            return repository.save(statusExistente);
        });
    }
}
