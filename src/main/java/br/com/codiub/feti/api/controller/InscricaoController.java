package br.com.codiub.feti.api.controller;


import br.com.codiub.feti.api.service.InscricaoRespostaService;
import br.com.codiub.feti.api.service.InscricaoService;
import br.com.codiub.feti.api.service.UserService;
import br.com.codiub.feti.model.entity.Inscricao;
import br.com.codiub.feti.model.entity.InscricaoResposta;
import br.com.codiub.feti.model.input.EditInscricaoInput;
import br.com.codiub.feti.model.input.InscricaoInput;
import br.com.codiub.feti.model.input.PerguntaRespostaInput;
import br.com.codiub.feti.model.output.InscricaoAllOutput;
import br.com.codiub.feti.model.output.InscricaoOutput;
import br.com.codiub.feti.model.output.InscricaoRespostaOutput;
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
@RequestMapping("/inscricao")
public class InscricaoController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private final InscricaoService inscricaoService;

    @Autowired
    private final InscricaoRespostaService inscricaoRespostaService;
    @Autowired
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody InscricaoInput inscricaoInput) {
        Inscricao inscricaoCreated = this.inscricaoService.save(inscricaoInput);
        List<InscricaoResposta> listInscricaoResposta =
                this.inscricaoRespostaService.save(inscricaoInput.getPerguntaResposta(), inscricaoCreated);

        List<InscricaoRespostaOutput> inscricaoRespostaOutput = listInscricaoResposta.stream()
                .map(InscricaoRespostaOutput::new)
                .collect(Collectors.toList());
        InscricaoAllOutput inscricaoOutput = new InscricaoAllOutput(inscricaoCreated, inscricaoRespostaOutput);


        return ResponseEntity.ok(null);
    }

    @GetMapping
    public ResponseEntity<List<InscricaoOutput>> listAll() {
        List<Inscricao> inscricaos = inscricaoService.listAll();
        List<InscricaoOutput> responseDTOS = inscricaos.stream()
                .map(inscricao -> new InscricaoOutput(inscricao))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/desativado")
    public ResponseEntity<List<InscricaoOutput>> listAllInscricao() {
        List<Inscricao> inscricaos = inscricaoService.listAllInscricaoDesactived();
        List<InscricaoOutput> responseDTOS = inscricaos.stream()
                .map(inscricao -> new InscricaoOutput(inscricao))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }


    @GetMapping("/{id}")
    public ResponseEntity<InscricaoOutput> getById(@PathVariable Long id) {
        Inscricao inscricao = inscricaoService.findById(id);
        InscricaoOutput inscricaoOutput = new InscricaoOutput(inscricao);
        return ResponseEntity.ok(inscricaoOutput);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<InscricaoOutput>> getByAllIdUser(@PathVariable Long id) {
        List<Inscricao> inscricao = inscricaoService.findByAllUser(id);
        List<InscricaoOutput> responseDTOS = inscricao.stream()
                .map(InscricaoOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/desativado/{id}")
    public ResponseEntity<InscricaoOutput> getByIdDesactived(@PathVariable Long id) {
        Inscricao inscricao = inscricaoService.findByIdDesactived(id);
        InscricaoOutput inscricaoOutput = new InscricaoOutput(inscricao);
        return ResponseEntity.ok(inscricaoOutput);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody @Valid EditInscricaoInput inscricaoInput) {
        Inscricao updatedInscricao = inscricaoService.updateById(id, inscricaoInput);
        InscricaoOutput inscricaoOutput = new InscricaoOutput(updatedInscricao);
        return ResponseEntity.ok(inscricaoOutput);
    }

    @PutMapping("/ativar/{id}")
    public ResponseEntity<?> ativarById(@PathVariable Long id) {
        Inscricao updatedInscricao = inscricaoService.ativarById(id);
        InscricaoOutput inscricaoOutput = new InscricaoOutput(updatedInscricao);
        return ResponseEntity.ok(inscricaoOutput);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<InscricaoOutput> deactivateById(@PathVariable Long id) {
        Inscricao deactivatedInscricao = inscricaoService.deactivateById(id);
        InscricaoOutput inscricaoOutput = new InscricaoOutput(deactivatedInscricao);
        return ResponseEntity.ok(inscricaoOutput);
    }

    @GetMapping("/infoAll/{id}")
    public ResponseEntity<InscricaoAllOutput> getInscricao(@PathVariable Long id) {
        Inscricao inscricao = inscricaoService.findById(id);
        List<InscricaoResposta> inscricaoResposta = inscricaoRespostaService.findByInscricao(inscricao.getId());
        List<InscricaoRespostaOutput> responseDTOS = inscricaoResposta.stream()
                .map(InscricaoRespostaOutput::new)
                .collect(Collectors.toList());
        InscricaoAllOutput inscricaoOutput = new InscricaoAllOutput(inscricao, responseDTOS);
        return ResponseEntity.ok(inscricaoOutput);
    }


}
