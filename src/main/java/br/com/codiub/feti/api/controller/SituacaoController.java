package br.com.codiub.feti.api.controller;

import br.com.codiub.feti.api.service.SituacaoService;
import br.com.codiub.feti.model.entity.Situacao;
import br.com.codiub.feti.model.output.SituacaoOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/situacao")
public class SituacaoController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private final SituacaoService situacaoService;


    @GetMapping
    public ResponseEntity<List<SituacaoOutput>> listAll() {
        List<Situacao> situacaos = situacaoService.listAllSituacao();
        List<SituacaoOutput> responseDTOS = situacaos.stream()
                .map(SituacaoOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }


    @GetMapping("/{id}")
    public ResponseEntity<SituacaoOutput> getById(@PathVariable Long id) {
        Situacao situacao = situacaoService.findById(id);
        SituacaoOutput situacaoOutput = new SituacaoOutput(situacao);
        return ResponseEntity.ok(situacaoOutput);
    }

}
