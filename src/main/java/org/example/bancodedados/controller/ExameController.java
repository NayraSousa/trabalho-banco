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

    @PutMapping("/atualizar")
    public Exame alterarExame(@RequestBody Exame exame){
        return exameService.alterarExame(exame);
    }

    @GetMapping("/buscar")
    public List<Exame> buscarTodos(){
        return exameService.buscarTodos();
    }
}
