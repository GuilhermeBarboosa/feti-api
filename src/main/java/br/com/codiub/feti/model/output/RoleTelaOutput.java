package br.com.codiub.feti.model.output;

import br.com.codiub.feti.model.entity.RoleTela;
import br.com.codiub.feti.model.entity.Tela;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleTelaOutput {
    private Long id;
    private Long idTela;
    private String identificador;
    private String descricao;
    private Long idRole;
    private String role;

    public RoleTelaOutput(RoleTela roleTela) {
        this.id = roleTela.getId();
        this.idTela = roleTela.getTela().getId();
        this.identificador = roleTela.getTela().getIdentificador();
        this.descricao = roleTela.getTela().getDescricao();
        this.idRole = roleTela.getRole().getId();
        this.role = roleTela.getRole().getRole();
    }

}
