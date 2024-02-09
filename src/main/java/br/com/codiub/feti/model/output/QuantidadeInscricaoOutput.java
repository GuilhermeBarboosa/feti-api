package br.com.codiub.feti.model.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
public class QuantidadeInscricaoOutput {
    @JsonProperty("edital")
    private String edital;
    @JsonProperty("qtdEdital")
    private Integer qtd;

    public QuantidadeInscricaoOutput() {}
}
