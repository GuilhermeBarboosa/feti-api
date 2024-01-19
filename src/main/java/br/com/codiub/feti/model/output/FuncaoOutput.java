package br.com.codiub.feti.model.output;

import br.com.codiub.feti.model.defaults.DefaultEntityDTO;
import br.com.codiub.feti.model.entity.Funcao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncaoOutput extends DefaultEntityDTO {
    private Long id;
    private String funcao;

    private Long idEdital;
    private String edital;
    public FuncaoOutput(Funcao funcao) {
        this.id = funcao.getId();
        this.funcao = funcao.getFuncao();
        this.idEdital = funcao.getEdital().getId();
        this.edital = funcao.getEdital().getEdital();
        this.setActived(funcao.getActived());
        this.setCreated(funcao.getCreated());
        this.setUpdated(funcao.getUpdated());
    }

}
