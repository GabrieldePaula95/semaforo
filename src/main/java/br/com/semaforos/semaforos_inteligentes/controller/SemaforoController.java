package br.com.semaforos.semaforos_inteligentes.controller;

import br.com.semaforos.semaforos_inteligentes.model.Semaforo;
import br.com.semaforos.semaforos_inteligentes.service.SemaforoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/semaforos")
public class SemaforoController {

    @Autowired
    private SemaforoService service;

    @GetMapping
    public ResponseEntity<List<Semaforo>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Semaforo> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Semaforo> salvar(@RequestBody Semaforo semaforo) {
        return ResponseEntity.ok(service.salvar(semaforo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Semaforo> atualizar(@PathVariable Long id, @RequestBody Semaforo semaforo) {
        return service.atualizar(id, semaforo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
