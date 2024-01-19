package br.com.codiub.feti.model.output;

import br.com.codiub.feti.model.entity.Pergunta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PerguntaAlternativaOutput {
    private Long idPergunta;
    private String pergunta;
    private List<AlternativaOutput> listAlternativas;

    public PerguntaAlternativaOutput(Pergunta pergunta) {
        this.idPergunta = pergunta.getId();
        this.pergunta = pergunta.getPergunta();
    }
}
