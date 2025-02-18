package org.example.bancodedados.DAO;

import org.example.bancodedados.conexaoJDBC.Conexao;
import org.example.bancodedados.model.Paciente;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PacienteDAO {

    Connection conexao = Conexao.getConexao();

    public PacienteDAO() throws SQLException {
    }

    public void inserir(Paciente paciente) throws SQLException {

        String sql = "insert into Pacientes (nome, data_nascimento, cpf, telefone, endereco) values (?, ?, ?, ?, ?)";

        PreparedStatement preparador = conexao.prepareStatement(sql);
        preparador.setString(1, paciente.getNome());
        preparador.setString(2, paciente.getDataNascimento());
        preparador.setString(3, paciente.getCpf());
        preparador.setString(4, paciente.getTelefone());
        preparador.setString(5, paciente.getEndereco());

        preparador.execute();
        preparador.close();
        System.out.println("Paciente cadastrado com Sucesso!!!");
    }

    public void excluir(int id) throws SQLException {
        String sql = "delete from Pacientes where id = ?";

        PreparedStatement preparador = conexao.prepareStatement(sql);
        preparador.setLong(1, id);
        preparador.execute();
        if (preparador.execute() == false) {
            System.out.println("Paciente não encontrado!");
        } else {
            System.out.println("Paciente excluído com Sucesso!!!");
        }
        preparador.close();
    }

    public Paciente alterar(Paciente paciente, int id) throws SQLException {
        String sql = "update Pacientes set nome = ?, data_nascimento = ?, cpf = ?, telefone = ?, endereco = ? where id = ?";

        PreparedStatement preparador = conexao.prepareStatement(sql);
        preparador.setString(1, paciente.getNome());
        preparador.setString(2, paciente.getDataNascimento());
        preparador.setString(3, paciente.getCpf());
        preparador.setString(4, paciente.getTelefone());
        preparador.setString(5, paciente.getEndereco());
        preparador.setInt(6, id);

        preparador.execute();
        preparador.close();
        System.out.println("Usuário atualizado com Sucesso!!!");
        return paciente;
    }

    public List<Paciente> buscar() throws SQLException {
        String sql = "select * from Pacientes";
        List<Paciente> pacientes = new ArrayList<Paciente>();
        PreparedStatement preparador = conexao.prepareStatement(sql);
        ResultSet resultado = preparador.executeQuery();

        while (resultado.next()) {
            Paciente paciente = new Paciente();
            paciente.setCpf(resultado.getString("cpf"));
            paciente.setNome(resultado.getString("nome"));
            paciente.setTelefone(resultado.getString("telefone"));
            paciente.setEndereco(resultado.getString("endereco"));
            paciente.setDataNascimento(resultado.getString("data_nascimento"));
            paciente.setId(resultado.getInt("id"));
            pacientes.add(paciente);
        }
        return pacientes;
    }

    public Paciente buscarId(int id) throws SQLException {
        String sql = "SELECT * FROM Pacientes WHERE id = ?";
        PreparedStatement preparador = conexao.prepareStatement(sql);
        preparador.setInt(1, id);

        ResultSet resultado = preparador.executeQuery();

        if (resultado.next()) {
            Paciente paciente = new Paciente();
            paciente.setCpf(resultado.getString("cpf"));
            paciente.setNome(resultado.getString("nome"));
            paciente.setTelefone(resultado.getString("telefone"));
            paciente.setEndereco(resultado.getString("endereco"));
            paciente.setDataNascimento(resultado.getString("data_nascimento"));
            paciente.setId(resultado.getInt("id"));
            return paciente;
        }

        return null;
    }
}
