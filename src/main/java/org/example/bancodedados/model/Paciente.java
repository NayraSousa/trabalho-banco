package org.example.bancodedados.model;

import lombok.Data;

@Data
public class Paciente {

    int id;
    String nome;
    String dataNascimento;
    String cpf;
    String telefone;
    String endereco;
}
