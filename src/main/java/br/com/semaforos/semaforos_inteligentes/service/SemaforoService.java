package br.com.semaforos.semaforos_inteligentes.service;

import br.com.semaforos.semaforos_inteligentes.model.Semaforo;
import br.com.semaforos.semaforos_inteligentes.repository.SemaforoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SemaforoService {

    @Autowired
    private SemaforoRepository repository;

    public List<Semaforo> listarTodos() {
        return repository.findAll();
    }

    public Optional<Semaforo> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Semaforo salvar(Semaforo semaforo) {
        return repository.save(semaforo);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Optional<Semaforo> atualizar(Long id, Semaforo semaforoAtualizado) {
        return repository.findById(id).map(semaforoExistente -> {
            semaforoExistente.setIdentificador(semaforoAtualizado.getIdentificador());
            semaforoExistente.setStatus(semaforoAtualizado.getStatus());
            semaforoExistente.setCruzamento(semaforoAtualizado.getCruzamento());
            return repository.save(semaforoExistente);
        });
    }
}
