package org.example.bancodedados.controller;

import org.example.bancodedados.model.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.bancodedados.service.MedicoService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    MedicoService medicoService;

    @PostMapping("/inserir")
    public void inserirMedico(@RequestBody Medico medico){
        medicoService.inserir(medico);
    }

    @DeleteMapping("/deletar/{id}")
    public void excluirMedico(@PathVariable int id){
        medicoService.excluir(id);
    }

    @PutMapping("/atualizar/{id}")
    public Medico alterarMedico(@RequestBody Medico medico,  @PathVariable int id) {
        return medicoService.alterar(medico, id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Medico>> buscarTodos(){
        return ResponseEntity.ok(medicoService.buscar());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Medico> buscarId(@PathVariable int id){
        return ResponseEntity.ok(medicoService.buscarId(id));
    }
}
