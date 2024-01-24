package br.com.codiub.feti.api.controller;


import br.com.codiub.feti.api.service.AlternativaService;
import br.com.codiub.feti.model.entity.Alternativa;
import br.com.codiub.feti.model.entity.Pergunta;
import br.com.codiub.feti.model.input.AlternativaInput;
import br.com.codiub.feti.model.output.AlternativaOutput;
import br.com.codiub.feti.model.output.PerguntaAlternativaOutput;
import br.com.codiub.feti.model.output.PerguntaOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/alternativa")
public class AlternativaController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private final AlternativaService alternativaService;


    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody AlternativaInput alternativaInput) {
        Alternativa createdAlternativa = alternativaService.save(alternativaInput);
        AlternativaOutput alternativaOutput = new AlternativaOutput(createdAlternativa);
        return ResponseEntity.ok(alternativaOutput);
    }

    @GetMapping
    public ResponseEntity<List<AlternativaOutput>> listAll() {
        List<Alternativa> alternativas = alternativaService.listAll();
        List<AlternativaOutput> responseDTOS = alternativas.stream()
                .map(AlternativaOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @PostMapping("/pergunta/{id}")
    public ResponseEntity<List<AlternativaOutput>> getAlternativaByFuncao(@PathVariable Long id) {
        List<Alternativa> alternativas = alternativaService.getAlternativaByFuncao(id);
        List<AlternativaOutput> responseDTOS = alternativas.stream()
                .map(AlternativaOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/desativado")
    public ResponseEntity<List<AlternativaOutput>> listAllAlternativa() {
        List<Alternativa> alternativas = alternativaService.listAllAlternativaDesactived();
        List<AlternativaOutput> responseDTOS = alternativas.stream()
                .map(AlternativaOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlternativaOutput> getById(@PathVariable Long id) {
        Alternativa alternativa = alternativaService.findById(id);
        AlternativaOutput alternativaOutput = new AlternativaOutput(alternativa);
        return ResponseEntity.ok(alternativaOutput);
    }

    @GetMapping("/desativado/{id}")
    public ResponseEntity<AlternativaOutput> getByIdDesactived(@PathVariable Long id) {
        Alternativa alternativa = alternativaService.findByIdDesactived(id);
        AlternativaOutput alternativaOutput = new AlternativaOutput(alternativa);
        return ResponseEntity.ok(alternativaOutput);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody @Valid AlternativaInput alternativaInput) {
        Alternativa updatedAlternativa = alternativaService.updateById(id, alternativaInput);
        AlternativaOutput alternativaOutput = new AlternativaOutput(updatedAlternativa);
        return ResponseEntity.ok(alternativaOutput);
    }

    @PutMapping("/ativar/{id}")
    public ResponseEntity<?> ativarById(@PathVariable Long id) {
        Alternativa updatedAlternativa = alternativaService.ativarById(id);
        AlternativaOutput alternativaOutput = new AlternativaOutput(updatedAlternativa);
        return ResponseEntity.ok(alternativaOutput);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AlternativaOutput> deactivateById(@PathVariable Long id) {
        Alternativa deactivatedAlternativa = alternativaService.deactivateById(id);
        AlternativaOutput alternativaOutput = new AlternativaOutput(deactivatedAlternativa);
        return ResponseEntity.ok(alternativaOutput);
    }
}
