package br.com.codiub.feti.model.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QuantidadeUserByMesOutput {
    @JsonProperty("data")
    private String mes_ano;
    @JsonProperty("qtdUser")
    private Integer qtd;

    public QuantidadeUserByMesOutput() {}
}
