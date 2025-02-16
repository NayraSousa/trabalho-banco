package model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Exame {
    int id;
    String descricao;
    String tipo;
    float preco;
    String dataDeAgendamento;
    Paciente paciente;
    Medico medico;
    Resultado resultado;
}
