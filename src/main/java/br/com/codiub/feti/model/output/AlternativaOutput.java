package br.com.codiub.feti.model.output;

import br.com.codiub.feti.model.defaults.DefaultEntityDTO;
import br.com.codiub.feti.model.entity.Alternativa;
import br.com.codiub.feti.model.entity.Pergunta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlternativaOutput extends DefaultEntityDTO {
    private Long id;
    private String alternativa;
    private Integer pontuacao;

    private Long idPergunta;
    private String pergunta;
    public AlternativaOutput(Alternativa alternativa) {
        this.id = alternativa.getId();
        this.alternativa = alternativa.getAlternativa();
        this.pontuacao = alternativa.getPontuacao();
        this.idPergunta = alternativa.getPergunta().getId();
        this.pergunta = alternativa.getPergunta().getPergunta();
        this.setActived(alternativa.getActived());
        this.setCreated(alternativa.getCreated());
        this.setUpdated(alternativa.getUpdated());
    }

}
