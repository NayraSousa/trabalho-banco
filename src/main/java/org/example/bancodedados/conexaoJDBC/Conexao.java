package org.example.bancodedados.conexaoJDBC;

import java.sql.*;

public class Conexao {

    private static Connection conexao = null;
    private static PreparedStatement preparador;
    private static ResultSet resultado;
    private static final String URL = "jdbc:mysql://localhost:3306/clinica?autoReconnect=true&useSSL=false";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";

    public static Connection getConexao() throws SQLException {
        if(conexao == null){
            try {
                conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
                System.out.println("Conexão estabelecida com sucesso!");
            } catch (SQLException e) {
                System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            }
        }
        return conexao;
    }
}
