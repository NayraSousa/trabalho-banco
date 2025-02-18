package org.example.bancodedados.controller;

import org.example.bancodedados.model.Paciente;
import org.example.bancodedados.model.Resultado;
import org.example.bancodedados.service.PacienteService;
import org.example.bancodedados.service.ResultadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resultados")
public class ResultadoController {

    @Autowired
    ResultadoService resultadoService;

    @PostMapping("/inserir")
    public void inserir(@RequestBody Resultado resultado){
        resultadoService.inserir(resultado);
    }

    @DeleteMapping("/deletar/{id}")
    public void excluir(@PathVariable int id){
        resultadoService.excluir(id);
    }

    @PutMapping("/atualizar/{id}")
    public Resultado alterar(@RequestBody Resultado resultado,  @PathVariable int id) {
        return resultadoService.alterar(resultado, id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Resultado>> buscarTodos(){
        return ResponseEntity.ok(resultadoService.buscar());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Resultado> buscarId(@PathVariable int id){
        return ResponseEntity.ok(resultadoService.buscarId(id));
    }
}
