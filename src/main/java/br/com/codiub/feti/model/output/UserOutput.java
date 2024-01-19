package br.com.codiub.feti.model.output;

import br.com.codiub.feti.model.defaults.DefaultEntityDTO;
import br.com.codiub.feti.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOutput extends DefaultEntityDTO {
    private Long id;
    private String name;
    private String email;
    private String password;


    private Long idRole;
    private String role;

    //PARTE DE CANDADITOS
    private String cpf;
    private Date data_de_nascimento;
    private String rua;
    private String bairro;
    private String cep;
    private String cidade;
    private String telefone;

    public UserOutput(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.password = user.getPassword();
        this.idRole = user.getRole().getId();
        this.role = user.getRole().getRole();
        this.data_de_nascimento = user.getData_de_nascimento();
        this.rua = user.getRua();
        this.cep = user.getCep();
        this.cidade = user.getCidade();
        this.bairro = user.getBairro();
        this.cpf = user.getCpf();
        this.telefone = user.getTelefone();
        this.setActived(user.getActived());
        this.setCreated(user.getCreated());
        this.setUpdated(user.getUpdated());
    }

}
