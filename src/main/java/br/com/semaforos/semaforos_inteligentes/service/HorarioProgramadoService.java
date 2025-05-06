package br.com.semaforos.semaforos_inteligentes.service;

import br.com.semaforos.semaforos_inteligentes.model.HorarioProgramado;
import br.com.semaforos.semaforos_inteligentes.repository.HorarioProgramadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioProgramadoService {

    private final HorarioProgramadoRepository repository;

    public HorarioProgramadoService(HorarioProgramadoRepository repository) {
        this.repository = repository;
    }

    public List<HorarioProgramado> listarTodos() {
        return repository.findAll();
    }

    public Optional<HorarioProgramado> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public HorarioProgramado atualizar(Long id, HorarioProgramado horarioProgramado) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Horário Programado com ID " + id + " não encontrado.");
        }
        horarioProgramado.setId(id);
        return repository.save(horarioProgramado);
    }


    public HorarioProgramado salvar(HorarioProgramado horarioProgramado) {
        return repository.save(horarioProgramado);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
