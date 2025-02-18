package org.example.bancodedados.service;

import org.example.bancodedados.DAO.ExameDAO;
import org.example.bancodedados.model.Exame;
import org.example.bancodedados.model.Medico;
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

    public Exame alterarExame(Exame exame, int id){
        Exame exameNovo = new Exame();

        try{
            exameNovo = exameDAO.alterarExame(exame, id);
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

    public Exame buscarId(int id){
        Exame exame = new Exame();

        try{
            exame = exameDAO.buscarId(id);
        } catch (SQLException e){
            System.err.println("Erro: " + e);
        }
        return exame;
    }
}
