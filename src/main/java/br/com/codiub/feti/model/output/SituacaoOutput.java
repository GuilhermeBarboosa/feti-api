package br.com.codiub.feti.model.output;

import br.com.codiub.feti.model.entity.Role;
import br.com.codiub.feti.model.entity.Situacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SituacaoOutput {
    private Long id;
    private String situacao;

    public SituacaoOutput(Situacao situacao){
        this.id = situacao.getId();
        this.situacao = situacao.getSituacao();
    }

}
