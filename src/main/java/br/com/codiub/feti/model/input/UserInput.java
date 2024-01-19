package br.com.codiub.feti.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserInput {

    private String name;

    private String email;

    private String password;

    private Long role;

    //PARTE DE CANDADITOS
    private String cpf;

    private Date data_de_nascimento;

    private String rua;

    private String bairro;

    private String cep;

    private String cidade;

    private String telefone;
}
