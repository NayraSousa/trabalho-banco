package org.example.bancodedados.service;

import org.example.bancodedados.DAO.MedicoDAO;
import org.example.bancodedados.model.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoDAO medicoDAO;

    public void inserir(Medico medico){
        try {
            medicoDAO.inserir(medico);
        } catch (SQLException e){
            System.err.println("Erro: " + e);
        }
    }

    public void excluir(int id){
        try{
            medicoDAO.excluir(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Medico alterar(Medico medico, int id) {
        Medico medicoNovo = new Medico();

        try{
            medicoNovo = medicoDAO.alterar(medico, id);
        } catch (SQLException e) {
            System.err.println("Erro: " + e);
        }
        return medicoNovo;
    }

    public List<Medico> buscar(){
        List<Medico> medicos = new ArrayList<Medico>();

        try{
            medicos = medicoDAO.buscar();
        } catch (SQLException e){
            System.err.println("Erro: " + e);
        }
        return medicos;
    }

    public Medico buscarId(int id){
        Medico medico = new Medico();

        try{
            medico = medicoDAO.buscarId(id);
        } catch (SQLException e){
            System.err.println("Erro: " + e);
        }
        return medico;
    }
}
