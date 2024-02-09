package br.com.codiub.feti.model.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QuantidadeAllOutput {
    @JsonProperty("qtdUser")
    private Integer qtd_usuarios;
    @JsonProperty("qtdEdital")
    private Integer qtd_editais;
    @JsonProperty("qtdInscricao")
    private Integer qtd_inscricoes;

    public QuantidadeAllOutput() {}
}
