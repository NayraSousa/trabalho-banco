package model;

import lombok.Data;

@Data
public class Paciente {

    int id;
    String nome;
    String data_de_nascimento;
    String cpf;
    String telefone;
    String endereço;
}
