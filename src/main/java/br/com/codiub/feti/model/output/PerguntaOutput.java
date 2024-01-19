package br.com.codiub.feti.model.output;

import br.com.codiub.feti.model.defaults.DefaultEntityDTO;
import br.com.codiub.feti.model.entity.Pergunta;
import br.com.codiub.feti.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerguntaOutput extends DefaultEntityDTO {
    private Long id;
    private String pergunta;

    private Long idFuncao;
    private String funcao;
    public PerguntaOutput(Pergunta pergunta) {
        this.id = pergunta.getId();
        this.pergunta = pergunta.getPergunta();
        this.idFuncao = pergunta.getFuncao().getId();
        this.funcao = pergunta.getFuncao().getFuncao();
        this.setActived(pergunta.getActived());
        this.setCreated(pergunta.getCreated());
        this.setUpdated(pergunta.getUpdated());
    }

}
