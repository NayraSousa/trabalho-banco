package org.example.bancodedados.service;

import org.example.bancodedados.DAO.ExameDAO;
import org.example.bancodedados.model.Exame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExameService {

    @Autowired
    private ExameDAO exameDAO;

    public void inserirExame(Exame exame){
        try {
            exameDAO.inserirExame(exame);
        } catch (SQLException e){
            System.err.println("Erro: " + e);
        }
    }

    public void excluirExame(int id){
        try{
            exameDAO.excluirExame(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Exame alterarExame(Exame exame){
        Exame exameNovo = new Exame();

        try{
            exameNovo = exameDAO.alterarExame(exame);
        } catch (SQLException e) {
            System.err.println("Erro: " + e);
        }
        return exameNovo;
    }

    public List<Exame> buscarTodos(){
        List<Exame> exames = new ArrayList<Exame>();

        try{
            exames = exameDAO.buscarTodos();
        } catch (SQLException e){
            System.err.println("Erro: " + e);
        }
        return exames;
    }
}
