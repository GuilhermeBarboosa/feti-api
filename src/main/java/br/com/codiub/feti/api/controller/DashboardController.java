package br.com.codiub.feti.api.controller;

import br.com.codiub.feti.api.service.DashboardService;
import br.com.codiub.feti.model.output.DashboardOutput;
import br.com.codiub.feti.model.output.GetQtdFuncaoByEditalOutput;
import br.com.codiub.feti.model.output.QuantidadeInscricaoOutput;
import br.com.codiub.feti.model.output.QuantidadeAllOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private final DashboardService dashboardService;


    @GetMapping
    public ResponseEntity<DashboardOutput> inscricaoPorEdital() {
        DashboardOutput dashboardOutput = new DashboardOutput();
        dashboardOutput.setQuantidadeInscricaoOutput(dashboardService.inscricaoPorEdital());
        dashboardOutput.setQuantidadeUserByMesOutput(dashboardService.quantidadeUsuariosByMes());
        dashboardOutput.setQuantidadeAllOutput(dashboardService.quantidadeAll());
        return ResponseEntity.ok(dashboardOutput);
    }
    @GetMapping("/inscricaoFuncaoByEdital/{id}")
    public ResponseEntity<List<GetQtdFuncaoByEditalOutput>> inscricaoFuncaoByEdital(@PathVariable Long id) {
        return ResponseEntity.ok(this.dashboardService.inscricaoFuncaoByEdital(id));
    }

}
