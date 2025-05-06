package br.com.semaforos.semaforos_inteligentes.controller;

import br.com.semaforos.semaforos_inteligentes.model.StatusSemaforo;
import br.com.semaforos.semaforos_inteligentes.service.StatusSemaforoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status-semaforo")
public class StatusSemaforoController {

    private final StatusSemaforoService service;

    public StatusSemaforoController(StatusSemaforoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<StatusSemaforo>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusSemaforo> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StatusSemaforo> salvar(@RequestBody StatusSemaforo statusSemaforo) {
        return ResponseEntity.ok(service.salvar(statusSemaforo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusSemaforo> atualizar(@PathVariable Long id, @RequestBody StatusSemaforo statusSemaforo) {
        return service.atualizar(id, statusSemaforo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
