package br.com.semaforos.semaforos_inteligentes.controller;

import br.com.semaforos.semaforos_inteligentes.model.HorarioProgramado;
import br.com.semaforos.semaforos_inteligentes.service.HorarioProgramadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/horarios-programados")
public class HorarioProgramadoController {

    @Autowired
    private HorarioProgramadoService service;

    @PostMapping
    public HorarioProgramado salvar(@RequestBody HorarioProgramado horario) {
        return service.salvar(horario);
    }

    @GetMapping
    public List<HorarioProgramado> listarTodos() {
        List<HorarioProgramado> horarios = service.listarTodos();
        // Força o carregamento do Semaforo para evitar null
        horarios.forEach(h -> h.getSemaforo().getIdentificador());
        return horarios;
    }

    @GetMapping("/{id}")
    public Optional<HorarioProgramado> buscarPorId(@PathVariable Long id) {
        Optional<HorarioProgramado> horario = service.buscarPorId(id);
        horario.ifPresent(h -> h.getSemaforo().getIdentificador()); // Força o carregamento do Semaforo
        return horario;
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioProgramado> atualizar(@PathVariable Long id, @RequestBody HorarioProgramado horario) {
        Optional<HorarioProgramado> horarioExistente = service.buscarPorId(id);

        if (horarioExistente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        horario.setId(id);

        HorarioProgramado atualizado = service.salvar(horario);

        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
