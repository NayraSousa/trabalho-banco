package org.example.bancodedados.model;

import lombok.Data;

@Data
public class Medico {
    int id;
    String nome;
    String especialidade;
    String crm;
    String telefone;
}
