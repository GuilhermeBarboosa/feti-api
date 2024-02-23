package br.com.codiub.feti.model.output;

import br.com.codiub.feti.model.defaults.DefaultEntityDTO;
import br.com.codiub.feti.model.entity.ArquivoInscricao;
import br.com.codiub.feti.model.entity.Inscricao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class InscricaoWithArquivosOutput extends DefaultEntityDTO {
    private InscricaoAllOutput inscricaoOutput;
    private List<ArquivoInscricaoOutput> arquivoInscricaoOutput;
    public InscricaoWithArquivosOutput(InscricaoAllOutput inscricao, List<ArquivoInscricaoOutput> arquivos) {
        this.inscricaoOutput = inscricao;
        this.arquivoInscricaoOutput = arquivos;
        setActived(inscricao.getActived());
        setCreated(inscricao.getCreated());
        setUpdated(inscricao.getUpdated());
    }

}
