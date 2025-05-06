package br.com.semaforos.semaforos_inteligentes.controller;

import br.com.semaforos.semaforos_inteligentes.model.Cruzamento;
import br.com.semaforos.semaforos_inteligentes.service.CruzamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cruzamentos")
public class CruzamentoController {

    private final CruzamentoService service;

    public CruzamentoController(CruzamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Cruzamento> criar(@RequestBody Cruzamento cruzamento) {
        return ResponseEntity.ok(service.salvar(cruzamento));
    }

    @GetMapping
    public ResponseEntity<List<Cruzamento>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cruzamento> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cruzamento> atualizar(@PathVariable Long id, @RequestBody Cruzamento cruzamento) {
        return service.atualizar(id, cruzamento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

