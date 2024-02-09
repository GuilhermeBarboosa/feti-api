package br.com.codiub.feti.model.output;

import lombok.Data;

import java.util.List;

@Data
public class DashboardOutput {
    private QuantidadeAllOutput quantidadeAllOutput;
    private List<QuantidadeInscricaoOutput> quantidadeInscricaoOutput;
    private List<QuantidadeUserByMesOutput> quantidadeUserByMesOutput;

}
