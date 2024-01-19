package br.com.codiub.feti.model.output;

import br.com.codiub.feti.model.defaults.DefaultEntityDTO;
import br.com.codiub.feti.model.entity.Funcao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PontuacaoOutput extends DefaultEntityDTO {
    private Long id;
    private Long pontuacao;

    private Long idInscricao;
    public PontuacaoOutput(Funcao funcao) {

    }

}
