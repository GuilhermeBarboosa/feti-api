package br.com.codiub.feti.model.output;

import br.com.codiub.feti.model.entity.Permissao;
import br.com.codiub.feti.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissaoOutput {
    private Long id;
    private String permissao;

    public PermissaoOutput(Permissao permissao){
        this.id = permissao.getId();
        this.permissao = permissao.getPermissao();
    }

}
