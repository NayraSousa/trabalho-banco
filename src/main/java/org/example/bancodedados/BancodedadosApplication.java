package org.example.bancodedados;

import conexaoJDBC.Conexao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
public class BancodedadosApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(BancodedadosApplication.class, args);
        Connection conexao = Conexao.getConexao();

        if (conexao != null) {
            System.out.println("Conectado ao banco!");
            try {
                conexao.close(); // Fechar conexão após o uso
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Falha na conexão.");
        }
    }
}
