package br.com.codiub.feti.model.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetQtdFuncaoByEditalOutput {
    @JsonProperty("edital")
    private String edital;
    @JsonProperty("funcao")
    private String funcao;
    @JsonProperty("qtdInscritos")
    private Integer quantidade_inscritos;

    public GetQtdFuncaoByEditalOutput() {}
}
