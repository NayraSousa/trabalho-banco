package org.example.bancodedados.DAO;

import org.example.bancodedados.conexaoJDBC.Conexao;
import org.example.bancodedados.model.Resultado;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ResultadoDAO {
    Connection conexao = Conexao.getConexao();

    public ResultadoDAO() throws SQLException {
    }

    public void inserir(Resultado resultado) throws SQLException {

        String sql = "insert into Resultados (resultado, data_resultado) values (?, ?)";

        PreparedStatement preparador = conexao.prepareStatement(sql);
        preparador.setString(1, resultado.getResultado());
        preparador.setString(2, resultado.getDataResultado());

        preparador.execute();
        preparador.close();
        System.out.println("Resultado cadastrado com Sucesso!!!");
    }

    public void excluir(int id) throws SQLException {
        String sql = "delete from Resultados where id = ?";

        PreparedStatement preparador = conexao.prepareStatement(sql);
        preparador.setLong(1, id);
        preparador.execute();
        if (preparador.execute() == false) {
            System.out.println("Resultado não encontrado!");
        } else {
            System.out.println("Resultado excluído com Sucesso!!!");
        }
        preparador.close();
    }

    public Resultado alterar(Resultado resultado, int id) throws SQLException {
        String sql = "update Resultados set resultado = ?, data_resultado = ? where id = ?";

        PreparedStatement preparador = conexao.prepareStatement(sql);
        preparador.setString(1, resultado.getResultado());
        preparador.setString(2, resultado.getDataResultado());
        preparador.setInt(3, id);

        preparador.execute();
        preparador.close();
        System.out.println("Usuário atualizado com Sucesso!!!");
        return resultado;
    }

    public List<Resultado> buscar() throws SQLException {
        String sql = "select * from Resultados";
        List<Resultado> resultados = new ArrayList<Resultado>();
        PreparedStatement preparador = conexao.prepareStatement(sql);
        ResultSet res = preparador.executeQuery();

        while (res.next()) {
            Resultado resultado = new Resultado();
            resultado.setResultado(res.getString("resultado"));
            resultado.setDataResultado(res.getString("data_resultado"));
            resultado.setId(res.getInt("id"));
            resultados.add(resultado);
        }
        return resultados;
    }

    public Resultado buscarId(int id) throws SQLException {
        String sql = "SELECT * FROM Resultados WHERE id = ?";
        PreparedStatement preparador = conexao.prepareStatement(sql);
        preparador.setInt(1, id);

        ResultSet res = preparador.executeQuery();

        if (res.next()) {
            Resultado resultado = new Resultado();
            resultado.setId(res.getInt("id"));
            resultado.setDataResultado(res.getString("data_resultado"));
            resultado.setResultado(res.getString("resultado"));
            return resultado;
        }

        return null;
    }

}
