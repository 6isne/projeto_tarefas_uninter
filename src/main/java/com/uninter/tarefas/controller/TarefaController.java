package com.uninter.tarefas.controller;

import com.uninter.tarefas.model.Tarefa;
import com.uninter.tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaRepository repository;


    @GetMapping
    public List<Tarefa> listarTarefas() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Tarefa> buscarPorId(@PathVariable Long id) {
        return repository.findById(id);
    }


    @PostMapping
    public Tarefa criarTarefa(@RequestBody Tarefa tarefa) {
        return repository.save(tarefa);
    }


    @PutMapping("/{id}")
    public Tarefa atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaAtualizada) {
        if (repository.existsById(id)) {
            tarefaAtualizada.setId(id);
            return repository.save(tarefaAtualizada);
        }
        return null;
    }


    @DeleteMapping("/{id}")
    public String removerTarefa(@PathVariable Long id) {
        repository.deleteById(id);
        return "Tarefa removida com sucesso.";
    }
}
