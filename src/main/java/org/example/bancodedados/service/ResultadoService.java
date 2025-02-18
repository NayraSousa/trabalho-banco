package org.example.bancodedados.service;

import org.example.bancodedados.DAO.ResultadoDAO;
import org.example.bancodedados.model.Resultado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResultadoService {

    @Autowired
    private ResultadoDAO resultadoDAO;

    public void inserir(Resultado resultado){
        try {
            resultadoDAO.inserir(resultado);
        } catch (SQLException e){
            System.err.println("Erro: " + e);
        }
    }

    public void excluir(int id){
        try{
            resultadoDAO.excluir(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Resultado alterar(Resultado resultado, int id) {
        Resultado resultadoNovo = new Resultado();

        try{
          resultadoNovo = resultadoDAO.alterar(resultado, id);
        } catch (SQLException e) {
            System.err.println("Erro: " + e);
        }
        return resultadoNovo;
    }

    public List<Resultado> buscar(){
        List<Resultado> resultados = new ArrayList<Resultado>();

        try{
            resultados = resultadoDAO.buscar();
        } catch (SQLException e){
            System.err.println("Erro: " + e);
        }
        return resultados;
    }

    public Resultado buscarId(int id){
        Resultado resultado = new Resultado();

        try{
            resultado = resultadoDAO.buscarId(id);
        } catch (SQLException e){
            System.err.println("Erro: " + e);
        }
        return resultado;
    }
}
