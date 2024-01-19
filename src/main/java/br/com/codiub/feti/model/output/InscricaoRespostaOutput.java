package br.com.codiub.feti.model.output;

import br.com.codiub.feti.model.defaults.DefaultEntityDTO;
import br.com.codiub.feti.model.entity.Inscricao;
import br.com.codiub.feti.model.entity.InscricaoResposta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscricaoRespostaOutput extends DefaultEntityDTO {
    private Long id;
    private Long idInscricao;
    private Long idPergunta;
    private String pergunta;
    private Long idAlternativa;
    private String alternativa;
    public InscricaoRespostaOutput(InscricaoResposta inscricaoResposta) {
        this.id = inscricaoResposta.getId();
        this.idInscricao = inscricaoResposta.getInscricao().getId();
        this.idPergunta = inscricaoResposta.getPergunta().getId();
        this.pergunta = inscricaoResposta.getPergunta().getPergunta();
        this.idAlternativa = inscricaoResposta.getAlternativa().getId();
        this.alternativa = inscricaoResposta.getAlternativa().getAlternativa();
        this.setActived(inscricaoResposta.getActived());
        this.setCreated(inscricaoResposta.getCreated());
        this.setUpdated(inscricaoResposta.getUpdated());
    }

}
