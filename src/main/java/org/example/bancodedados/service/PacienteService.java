package org.example.bancodedados.service;

import org.example.bancodedados.DAO.PacienteDAO;
import org.example.bancodedados.model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteDAO pacienteDAO;

    public void inserir(Paciente paciente){
        try {
            pacienteDAO.inserir(paciente);
        } catch (SQLException e){
            System.err.println("Erro: " + e);
        }
    }

    public void excluir(int id){
        try{
            pacienteDAO.excluir(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Paciente alterar(Paciente paciente, int id) {
        Paciente pacienteNovo = new Paciente();

        try{
            pacienteNovo = pacienteDAO.alterar(paciente, id);
        } catch (SQLException e) {
            System.err.println("Erro: " + e);
        }
        return pacienteNovo;
    }

    public List<Paciente> buscar(){
        List<Paciente> pacientes = new ArrayList<Paciente>();

        try{
            pacientes = pacienteDAO.buscar();
        } catch (SQLException e){
            System.err.println("Erro: " + e);
        }
        return pacientes;
    }

    public Paciente buscarId(int id){
        Paciente paciente = new Paciente();

        try{
            paciente = pacienteDAO.buscarId(id);
        } catch (SQLException e){
            System.err.println("Erro: " + e);
        }
        return paciente;
    }
}
