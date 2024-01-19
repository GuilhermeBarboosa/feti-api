package br.com.codiub.feti.model.output;

import br.com.codiub.feti.model.defaults.DefaultEntityDTO;
import br.com.codiub.feti.model.entity.Edital;
import br.com.codiub.feti.model.entity.Funcao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditalOutput extends DefaultEntityDTO {
    private Long id;
    private String edital;
    private Date data_inicio;
    private Date data_fim;
    private Integer qtd_vagas;
    private String arquivo;

    public EditalOutput(Edital edital) {
        this.id = edital.getId();
        this.edital = edital.getEdital();
        this.data_inicio = edital.getData_inicio();
        this.data_fim = edital.getData_fim();
        this.qtd_vagas = edital.getQtd_vagas();
        this.arquivo = edital.getArquivo();

        this.setActived(edital.getActived());
        this.setCreated(edital.getCreated());
        this.setUpdated(edital.getUpdated());
    }

}
