package br.com.codiub.feti.api.controller;


import br.com.codiub.feti.api.service.PerguntaService;
import br.com.codiub.feti.model.entity.Pergunta;
import br.com.codiub.feti.model.input.PerguntaInput;
import br.com.codiub.feti.model.output.FuncaoOutput;
import br.com.codiub.feti.model.output.PerguntaAlternativaOutput;
import br.com.codiub.feti.model.output.PerguntaOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pergunta")
public class PerguntaController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private final PerguntaService perguntaService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody PerguntaInput perguntaInput) {
        Pergunta createdPergunta = perguntaService.save(perguntaInput);
        PerguntaOutput perguntaOutput = new PerguntaOutput(createdPergunta);
        return ResponseEntity.ok(perguntaOutput);
    }

    @GetMapping
    public ResponseEntity<List<PerguntaOutput>> listAll() {
        List<Pergunta> perguntas = perguntaService.listAll();
        List<PerguntaOutput> responseDTOS = perguntas.stream()
                .map(PerguntaOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/desativado")
    public ResponseEntity<List<PerguntaOutput>> listAllPergunta() {
        List<Pergunta> perguntas = perguntaService.listAllPerguntaDesactived();
        List<PerguntaOutput> responseDTOS = perguntas.stream()
                .map(PerguntaOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerguntaOutput> getById(@PathVariable Long id) {
        Pergunta pergunta = perguntaService.findById(id);
        PerguntaOutput perguntaOutput = new PerguntaOutput(pergunta);
        return ResponseEntity.ok(perguntaOutput);
    }

    @GetMapping("/funcao/{id}")
    public ResponseEntity<List<PerguntaOutput>> getPerguntaByFuncao(@PathVariable Long id) {
        List<Pergunta> perguntas = perguntaService.listPerguntasWithFuncao(id);
        List<PerguntaOutput> responseDTOS = perguntas.stream()
                .map(PerguntaOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/desativado/{id}")
    public ResponseEntity<PerguntaOutput> getByIdDesactived(@PathVariable Long id) {
        Pergunta pergunta = perguntaService.findByIdDesactived(id);
        PerguntaOutput perguntaOutput = new PerguntaOutput(pergunta);
        return ResponseEntity.ok(perguntaOutput);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody @Valid PerguntaInput perguntaInput) {
        Pergunta updatedPergunta = perguntaService.updateById(id, perguntaInput);
        PerguntaOutput perguntaOutput = new PerguntaOutput(updatedPergunta);
        return ResponseEntity.ok(perguntaOutput);
    }

    @PutMapping("/ativar/{id}")
    public ResponseEntity<?> ativarById(@PathVariable Long id) {
        Pergunta updatedPergunta = perguntaService.ativarById(id);
        PerguntaOutput perguntaOutput = new PerguntaOutput(updatedPergunta);
        return ResponseEntity.ok(perguntaOutput);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PerguntaOutput> deactivateById(@PathVariable Long id) {
        Pergunta deactivatedPergunta = perguntaService.deactivateById(id);
        PerguntaOutput perguntaOutput = new PerguntaOutput(deactivatedPergunta);
        return ResponseEntity.ok(perguntaOutput);
    }
}
