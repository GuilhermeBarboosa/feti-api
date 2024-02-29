package br.com.codiub.feti.model.output;

import br.com.codiub.feti.model.entity.Role;
import br.com.codiub.feti.model.entity.Tela;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelaOutput {
    private Long id;
    private String identificador;
    private String descricao;

    public TelaOutput(Tela tela){
        this.id = tela.getId();
        this.identificador = tela.getIdentificador();
        this.descricao = tela.getDescricao();
    }

}
