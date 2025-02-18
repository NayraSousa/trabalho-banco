package org.example.bancodedados.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Exame {
    int id;
    String descricao;
    String tipo;
    float preco;
    String dataAgendamento;
    Paciente paciente;
    Medico medico;
    Resultado resultado;
}
