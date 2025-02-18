package org.example.bancodedados.DAO;

import org.example.bancodedados.conexaoJDBC.Conexao;
import org.example.bancodedados.model.Exame;
import org.example.bancodedados.model.Medico;
import org.example.bancodedados.model.Paciente;
import org.example.bancodedados.model.Resultado;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExameDAO {
    Connection conexao = Conexao.getConexao();

    MedicoDAO medicoDAO = new MedicoDAO();
    PacienteDAO pacienteDAO = new PacienteDAO();
    ResultadoDAO resultadoDAO = new ResultadoDAO();

    public ExameDAO() throws SQLException {
    }

    public void inserirExame(Exame exame) throws SQLException {

        String sql = "insert into Exames (descricao, tipo, preco ,data_agendamento, paciente_id, medico_id, resultado_id) values (?, ?, ?, ?, ?, ?, ?)";

        Medico medico = medicoDAO.buscarId(exame.getMedico().getId());
        Resultado resultado = resultadoDAO.buscarId(exame.getResultado().getId());
        Paciente paciente = pacienteDAO.buscarId(exame.getPaciente().getId());

            PreparedStatement preparador = conexao.prepareStatement(sql);
            preparador.setString(1, exame.getDescricao());
            preparador.setString(2, exame.getTipo());
            preparador.setFloat(3, exame.getPreco());
            preparador.setString(4, exame.getDataAgendamento());
            preparador.setInt(5, paciente.getId());
            preparador.setInt(6, medico.getId());
            preparador.setInt(7, resultado.getId());

            preparador.execute();
            preparador.close();
            System.out.println("Usuário cadastrado com Sucesso!!!");
    }

    public void excluirExame(int id) throws SQLException {
        String sql = "delete from Exames where id = ?";

            PreparedStatement preparador = conexao.prepareStatement(sql);
            preparador.setLong(1, id);
            preparador.execute();
            if (preparador.execute() == false) {
                System.out.println("Usuario não encontrado");
            } else {
                System.out.println("Usuário excluído com Sucesso!!!");
            }
            preparador.close();
    }

    public Exame alterarExame(Exame exame) throws SQLException {
        String sql = "update Exames set descricao = ?, tipo = ?, preco = ?, data_agendamento = ?" +
                "paciente_id = ?, medico_id = ?, resultado_id = ?";

            PreparedStatement preparador = conexao.prepareStatement(sql);
            preparador.setString(1, exame.getDescricao());
            preparador.setString(2, exame.getTipo());
            preparador.setFloat(3, exame.getPreco());
            preparador.setString(4, exame.getDataAgendamento());
            preparador.setInt(5, exame.getPaciente().getId());
            preparador.setInt(6, exame.getMedico().getId());
            preparador.setInt(7, exame.getResultado().getId());

            preparador.execute();
            preparador.close();
            System.out.println("Usuário atualizado com Sucesso!!!");
        return exame;
    }

    public List<Exame> buscarTodos() throws SQLException {
        String sql = "SELECT * FROM Exames";
        List<Exame> lista = new ArrayList<Exame>();
            PreparedStatement preparador = conexao.prepareStatement(sql);
            ResultSet resultado = preparador.executeQuery();

            while (resultado.next()) {
                Exame exame = new Exame();

                exame.setId(resultado.getInt("id"));
                exame.setDescricao(resultado.getString("descricao"));
                exame.setTipo(resultado.getString("tipo"));
                exame.setPreco(resultado.getFloat("preco"));
                exame.setDataAgendamento(resultado.getString("data_agendamento"));
                exame.setPaciente((Paciente) resultado.getObject("id"));
                exame.setMedico((Medico) resultado.getObject("id"));
                exame.setResultado((Resultado) resultado.getObject("id"));

                lista.add(exame);
            }
        return lista;
    }
}
