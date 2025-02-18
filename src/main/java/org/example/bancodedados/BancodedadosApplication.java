package org.example.bancodedados;

import org.example.bancodedados.conexaoJDBC.Conexao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
public class BancodedadosApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(BancodedadosApplication.class, args);
        Connection conexao = Conexao.getConexao();

    }
}
