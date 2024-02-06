package br.com.codiub.feti.model.output;

import br.com.codiub.feti.model.defaults.DefaultEntityDTO;
import br.com.codiub.feti.model.entity.Funcao;
import br.com.codiub.feti.model.entity.Inscricao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscricaoOutput extends DefaultEntityDTO {
    private Long id;
    private Long idUser;
    private String user;
    private Long idEdital;
    private String edital;
    private Long idFuncao;
    private String funcao;
    private Long pontuacao;
    private String situacao;
    private Long idSituacao;

    public InscricaoOutput(Inscricao inscricao) {
        this.id = inscricao.getId();
        this.idUser = inscricao.getUsuario().getId();
        this.user = inscricao.getUsuario().getName();
        this.idEdital = inscricao.getEdital().getId();
        this.edital = inscricao.getEdital().getEdital();
        this.idFuncao = inscricao.getFuncao().getId();
        this.funcao = inscricao.getFuncao().getFuncao();
        this.pontuacao = inscricao.getPontuacao();
        this.situacao = inscricao.getSituacao().getSituacao();
        this.idSituacao = inscricao.getSituacao().getId();
        this.setActived(inscricao.getActived());
        this.setCreated(inscricao.getCreated());
        this.setUpdated(inscricao.getUpdated());
    }

}
