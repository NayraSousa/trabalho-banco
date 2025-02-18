package org.example.bancodedados.controller;

import org.example.bancodedados.model.Exame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.example.bancodedados.service.ExameService;

import java.util.List;

@RequestMapping("/exames")
@RestController
public class ExameController {

    @Autowired
    ExameService exameService;

    @PostMapping("/inserir")
    public void inserirExame(@RequestBody Exame exame){
        exameService.inserirExame(exame);
    }

    @DeleteMapping("/deletar/{id}")
    public void excluirExame(@PathVariable int id){
        exameService.excluirExame(id);
    }

    @PutMapping("/atualizar/{id}")
    public Exame alterarExame(@RequestBody Exame exame, @PathVariable int id){
        return exameService.alterarExame(exame, id);
    }

    @GetMapping("/buscar")
    public List<Exame> buscarTodos(){
        return exameService.buscarTodos();
    }

    @GetMapping("/buscar/{id}")
    public Exame buscarId(@PathVariable int id){
        return exameService.buscarId(id);
    }
}
