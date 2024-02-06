package br.com.codiub.feti.model.output;

import br.com.codiub.feti.model.defaults.DefaultEntityDTO;
import br.com.codiub.feti.model.entity.Inscricao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscricaoAllOutput extends DefaultEntityDTO {
    private Long id;
    private Long idUser;
    private String user;
    private String cpf;
    private String email;
    private String telefone;
    private Date data_de_nascimento;

    private Long idEdital;
    private String edital;
    private Long idFuncao;
    private String funcao;
    private Long pontuacao;
    private String situacao;
    private Long idSituacao;

    private List<InscricaoRespostaOutput> inscricaoResposta;
    public InscricaoAllOutput(Inscricao inscricao, List<InscricaoRespostaOutput> inscricaoRespostaOutput) {
        this.id = inscricao.getId();
        this.idUser = inscricao.getUsuario().getId();
        this.user = inscricao.getUsuario().getName();
        this.cpf = inscricao.getUsuario().getCpf();
        this.email = inscricao.getUsuario().getEmail();
        this.telefone = inscricao.getUsuario().getTelefone();
        this.data_de_nascimento = inscricao.getUsuario().getData_de_nascimento();
        this.idEdital = inscricao.getEdital().getId();
        this.edital = inscricao.getEdital().getEdital();
        this.idFuncao = inscricao.getFuncao().getId();
        this.funcao = inscricao.getFuncao().getFuncao();
        this.pontuacao = inscricao.getPontuacao();
        this.situacao = inscricao.getSituacao().getSituacao();
        this.idSituacao = inscricao.getSituacao().getId();
        this.inscricaoResposta = inscricaoRespostaOutput;
        this.setActived(inscricao.getActived());
        this.setCreated(inscricao.getCreated());
        this.setUpdated(inscricao.getUpdated());
    }

    public InscricaoAllOutput(Inscricao inscricao) {
        this.id = inscricao.getId();
        this.idUser = inscricao.getUsuario().getId();
        this.user = inscricao.getUsuario().getName();
        this.idEdital = inscricao.getEdital().getId();
        this.edital = inscricao.getEdital().getEdital();
        this.idFuncao = inscricao.getFuncao().getId();
        this.funcao = inscricao.getFuncao().getFuncao();
        this.situacao = inscricao.getSituacao().getSituacao();
        this.idSituacao = inscricao.getSituacao().getId();
        this.pontuacao = inscricao.getPontuacao();
        this.setActived(inscricao.getActived());
        this.setCreated(inscricao.getCreated());
        this.setUpdated(inscricao.getUpdated());
    }

}
