package br.com.codiub.feti.model.output;

import br.com.codiub.feti.model.defaults.DefaultEntityDTO;
import br.com.codiub.feti.model.entity.ArquivoInscricao;
import br.com.codiub.feti.model.entity.Pergunta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArquivoInscricaoOutput extends DefaultEntityDTO {
    private Long id;
    private String caminho_arquivo;

    private Long idInscricao;
    private Long idUser;
    private String user;
    private Long idEdital;
    private String edital;
    public ArquivoInscricaoOutput(ArquivoInscricao arquivo) {
        Pattern pattern = Pattern.compile("\\{.*?\\}");
        Matcher matcher = pattern.matcher(arquivo.getCaminho_arquivo());
        String resultado = matcher.replaceAll("");
        this.caminho_arquivo = resultado;

        this.id = arquivo.getId();
        this.idInscricao = arquivo.getInscricao().getId();
        this.idUser = arquivo.getInscricao().getUsuario().getId();
        this.user = arquivo.getInscricao().getUsuario().getName();
        this.idEdital = arquivo.getInscricao().getEdital().getId();
        this.edital = arquivo.getInscricao().getEdital().getEdital();

        this.setActived(arquivo.getActived());
        this.setCreated(arquivo.getCreated());
        this.setUpdated(arquivo.getUpdated());
    }

}
