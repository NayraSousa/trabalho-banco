package org.example.bancodedados.DAO;

import org.example.bancodedados.conexaoJDBC.Conexao;
import org.example.bancodedados.model.Exame;
import org.example.bancodedados.model.Medico;
import org.example.bancodedados.model.Paciente;
import org.example.bancodedados.model.Resultado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExameDAO {
    Connection conexao = Conexao.getConexao();

    @Autowired
    MedicoDAO medicoDAO;

    @Autowired
    PacienteDAO pacienteDAO;

    @Autowired
    ResultadoDAO resultadoDAO;

    public ExameDAO() throws SQLException {
    }

    public void inserirExame(Exame exame) throws SQLException {
        String sql = "INSERT INTO Exames (descricao, tipo, preco, data_agendamento, paciente_id, medico_id, resultado_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparador = conexao.prepareStatement(sql);
        preparador.setString(1, exame.getDescricao());
        preparador.setString(2, exame.getTipo());
        preparador.setFloat(3, exame.getPreco());
        preparador.setString(4, exame.getDataAgendamento());

        int idPaciente = exame.getPaciente().getId();
        int idMedico = exame.getMedico().getId();
        int idResultado = exame.getResultado().getId();

        Paciente paciente = pacienteDAO.buscarId(idPaciente);
        Medico medico = medicoDAO.buscarId(idMedico);
        Resultado resultadoExame = resultadoDAO.buscarId(idResultado);

        exame.setPaciente(paciente);
        exame.setMedico(medico);
        exame.setResultado(resultadoExame);

        preparador.setInt(5, exame.getPaciente().getId());
        preparador.setInt(6, exame.getMedico().getId());
        preparador.setInt(7, exame.getResultado().getId());
        preparador.execute();
        preparador.close();

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

    public Exame alterarExame(Exame exame, int id) throws SQLException {
        String sql = "update Exames set descricao = ?, tipo = ?, preco = ?, data_agendamento = ?," +
                "paciente_id = ?, medico_id = ?, resultado_id = ? where id = ?";

        PreparedStatement preparador = conexao.prepareStatement(sql);
        preparador.setString(1, exame.getDescricao());
        preparador.setString(2, exame.getTipo());
        preparador.setFloat(3, exame.getPreco());
        preparador.setString(4, exame.getDataAgendamento());

        int idPaciente = exame.getPaciente().getId();
        int idMedico = exame.getMedico().getId();
        int idResultado = exame.getResultado().getId();

        Paciente paciente = pacienteDAO.buscarId(idPaciente);
        Medico medico = medicoDAO.buscarId(idMedico);
        Resultado resultadoExame = resultadoDAO.buscarId(idResultado);

        exame.setPaciente(paciente);
        exame.setMedico(medico);
        exame.setResultado(resultadoExame);

        preparador.setInt(5, exame.getPaciente().getId());
        preparador.setInt(6, exame.getMedico().getId());
        preparador.setInt(7, exame.getResultado().getId());
        preparador.setInt(8, id);
        preparador.execute();
        preparador.close();
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

                int idPaciente = resultado.getInt("paciente_id");
                int idMedico = resultado.getInt("medico_id");
                int idResultado = resultado.getInt("resultado_id");

                Paciente paciente = pacienteDAO.buscarId(idPaciente);
                Medico medico = medicoDAO.buscarId(idMedico);
                Resultado resultadoExame = resultadoDAO.buscarId(idResultado);

                exame.setPaciente(paciente);
                exame.setMedico(medico);
                exame.setResultado(resultadoExame);

                lista.add(exame);
            }
        return lista;
    }
    public Exame buscarId(int id) throws SQLException {
        String sql = "SELECT * FROM Exames WHERE id = ?";
        PreparedStatement preparador = conexao.prepareStatement(sql);
        preparador.setInt(1, id);

        ResultSet resultado = preparador.executeQuery();

        if (resultado.next()) {
            Exame exame = new Exame();
            exame.setId(resultado.getInt("id"));
            exame.setDescricao(resultado.getString("descricao"));
            exame.setPreco(resultado.getFloat("preco"));
            exame.setTipo(resultado.getString("tipo"));
            exame.setDataAgendamento(resultado.getString("data_agendamento"));

            int idPaciente = resultado.getInt("paciente_id");
            int idMedico = resultado.getInt("medico_id");
            int idResultado = resultado.getInt("resultado_id");

            Paciente paciente = pacienteDAO.buscarId(idPaciente);
            Medico medico = medicoDAO.buscarId(idMedico);
            Resultado resultadoExame = resultadoDAO.buscarId(idResultado);

            exame.setPaciente(paciente);
            exame.setMedico(medico);
            exame.setResultado(resultadoExame);

            return exame;
        }

        return null;
    }
}
