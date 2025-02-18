package org.example.bancodedados.DAO;

import org.example.bancodedados.conexaoJDBC.Conexao;
import org.example.bancodedados.model.Medico;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MedicoDAO {
    Connection conexao = Conexao.getConexao();

    public MedicoDAO() throws SQLException {
    }

    public void inserir(Medico medico) throws SQLException {

        String sql = "insert into Medicos (nome, especialidade, crm, telefone) values (?, ?, ?, ?)";

        PreparedStatement preparador = conexao.prepareStatement(sql);
        preparador.setString(1, medico.getNome());
        preparador.setString(2, medico.getEspecialidade());
        preparador.setString(3, medico.getCrm());
        preparador.setString(4, medico.getTelefone());

        preparador.execute();
        preparador.close();
        System.out.println("Médico cadastrado com Sucesso!!!");
    }

    public void excluir(int id) throws SQLException {
        String sql = "delete from Medicos where id = ?";

        PreparedStatement preparador = conexao.prepareStatement(sql);
        preparador.setLong(1, id);
        preparador.execute();
        if (preparador.execute() == false) {
            System.out.println("Médico não encontrado!");
        } else {
            System.out.println("Médico excluído com Sucesso!!!");
        }
        preparador.close();
    }

    public Medico alterar(Medico medico, int id) throws SQLException {
        String sql = "update Medicos set nome = ?, especialidade = ?, crm = ?, telefone = ? where id = ?";

        PreparedStatement preparador = conexao.prepareStatement(sql);
        preparador.setString(1, medico.getNome());
        preparador.setString(2, medico.getEspecialidade());
        preparador.setString(3, medico.getCrm());
        preparador.setString(4, medico.getTelefone());
        preparador.setInt(5, id);

        preparador.execute();
        preparador.close();
        System.out.println("Usuário atualizado com Sucesso!!!");
        return medico;
    }

    public List<Medico> buscar() throws SQLException {
        String sql = "select * from Medicos";
        List<Medico> medicos = new ArrayList<Medico>();
        PreparedStatement preparador = conexao.prepareStatement(sql);
        ResultSet resultado = preparador.executeQuery();

        while (resultado.next()) {
            Medico medico = new Medico();
            medico.setCrm(resultado.getString("crm"));
            medico.setNome(resultado.getString("nome"));
            medico.setTelefone(resultado.getString("telefone"));
            medico.setEspecialidade(resultado.getString("especialidade"));
            medico.setId(resultado.getInt("id"));
            medicos.add(medico);
        }
        return medicos;
    }

    public Medico buscarId(int id) throws SQLException {
        String sql = "SELECT * FROM Medicos WHERE id = ?";
        PreparedStatement preparador = conexao.prepareStatement(sql);
        preparador.setInt(1, id);

        ResultSet resultado = preparador.executeQuery();

        if (resultado.next()) {
            Medico medico = new Medico();
            medico.setId(resultado.getInt("id"));
            medico.setNome(resultado.getString("nome"));
            medico.setEspecialidade(resultado.getString("especialidade"));
            medico.setTelefone(resultado.getString("telefone"));
            return medico;
        }

        return null;
    }

}
