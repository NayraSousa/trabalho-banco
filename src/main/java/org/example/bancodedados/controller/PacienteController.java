package org.example.bancodedados.controller;

import org.example.bancodedados.model.Paciente;
import org.example.bancodedados.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    PacienteService pacienteService;

    @PostMapping("/inserir")
    public void inserir(@RequestBody Paciente paciente){
        pacienteService.inserir(paciente);
    }

    @DeleteMapping("/deletar/{id}")
    public void excluir(@PathVariable int id){
        pacienteService.excluir(id);
    }

    @PutMapping("/atualizar/{id}")
    public Paciente alterar(@RequestBody Paciente paciente,  @PathVariable int id) {
        return pacienteService.alterar(paciente, id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Paciente>> buscarTodos(){
        return ResponseEntity.ok(pacienteService.buscar());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Paciente> buscarId(@PathVariable int id){
        return ResponseEntity.ok(pacienteService.buscarId(id));
    }
}
