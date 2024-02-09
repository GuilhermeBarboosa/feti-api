package br.com.codiub.feti.api.service;

import br.com.codiub.feti.model.output.QuantidadeInscricaoOutput;
import br.com.codiub.feti.model.output.QuantidadeAllOutput;
import br.com.codiub.feti.model.output.QuantidadeUserByMesOutput;
import br.com.codiub.feti.repository.DashboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {
    @Autowired
    private final DashboardRepository dashboardRepository;


    public List<QuantidadeInscricaoOutput> inscricaoPorEdital() {
        return dashboardRepository.getTotalInscricoes();
    }

    public List<QuantidadeUserByMesOutput> quantidadeUsuariosByMes() {
        return dashboardRepository.getQuantidadeUsuariosByMes();
    }

    public QuantidadeAllOutput quantidadeAll() {
        return dashboardRepository.getQuantidadeAll();
    }
}
