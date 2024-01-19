package br.com.codiub.feti.api.controller;


import br.com.codiub.feti.api.service.FuncaoService;
import br.com.codiub.feti.api.service.InscricaoService;
import br.com.codiub.feti.model.entity.Edital;
import br.com.codiub.feti.model.entity.Funcao;
import br.com.codiub.feti.model.entity.Inscricao;
import br.com.codiub.feti.model.input.FuncaoInput;
import br.com.codiub.feti.model.input.VerifyInput;
import br.com.codiub.feti.model.output.EditalOutput;
import br.com.codiub.feti.model.output.FuncaoOutput;
import br.com.codiub.feti.model.output.InscricaoOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/funcao")
public class FuncaoController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private final FuncaoService funcaoService;

    @Autowired
    private final InscricaoService inscricaoService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody FuncaoInput funcaoInput) {
        Funcao createdFuncao = funcaoService.save(funcaoInput);
        FuncaoOutput funcaoOutput = new FuncaoOutput(createdFuncao);
        return ResponseEntity.ok(funcaoOutput);
    }

    @GetMapping
    public ResponseEntity<List<FuncaoOutput>> listAll() {
        List<Funcao> funcaos = funcaoService.listAll();
        List<FuncaoOutput> responseDTOS = funcaos.stream()
                .map(FuncaoOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/desativado")
    public ResponseEntity<List<FuncaoOutput>> listAllFuncao() {
        List<Funcao> funcaos = funcaoService.listAllFuncaoDesactived();
        List<FuncaoOutput> responseDTOS = funcaos.stream()
                .map(FuncaoOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }


    @GetMapping("/{id}")
    public ResponseEntity<FuncaoOutput> getById(@PathVariable Long id) {
        Funcao funcao = funcaoService.findById(id);
        FuncaoOutput funcaoOutput = new FuncaoOutput(funcao);
        return ResponseEntity.ok(funcaoOutput);
    }

    @GetMapping("/edital/{idEdital}")
    public ResponseEntity<List<FuncaoOutput>> getByIdEdital(@PathVariable Long idEdital) {
        List<Funcao> funcaos = funcaoService.findByIdEdital(idEdital);
        List<FuncaoOutput> responseDTOS = funcaos.stream()
                .map(FuncaoOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);

    }

    @PostMapping("/verifyInscricao")
    public ResponseEntity<?> getVerifyEdital(@Valid @RequestBody VerifyInput verifyInput) {

        System.out.println("verifyInput: " + verifyInput.toString());
        Optional<Inscricao> inscricaoOptional = this.inscricaoService.getVerifyEdital(verifyInput);

        if (inscricaoOptional.isPresent()) {
            InscricaoOutput inscricaoOutput = new InscricaoOutput(inscricaoOptional.get());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Inscrição já existente");
        } else {
            return ResponseEntity.ok().body("{\"message\": \"Inscrição permitida\"}");
        }
    }



    @GetMapping("/desativado/{id}")
    public ResponseEntity<FuncaoOutput> getByIdDesactived(@PathVariable Long id) {
        Funcao funcao = funcaoService.findByIdDesactived(id);
        FuncaoOutput funcaoOutput = new FuncaoOutput(funcao);
        return ResponseEntity.ok(funcaoOutput);
    }
//    @GetMapping("/cpf/{cpf}")
//    public ResponseEntity<List<FuncaoOutput>> getByCpf(@PathVariable String cpf) {
//        List<Funcao> funcaos = funcaoService.findByCpf(cpf);
//        List<FuncaoOutput> responseDTOS = funcaos.stream()
//                .map(FuncaoOutput::new)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(responseDTOS);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody @Valid FuncaoInput funcaoInput) {
        Funcao updatedFuncao = funcaoService.updateById(id, funcaoInput);
        FuncaoOutput funcaoOutput = new FuncaoOutput(updatedFuncao);
        return ResponseEntity.ok(funcaoOutput);
    }

    @PutMapping("/ativar/{id}")
    public ResponseEntity<?> ativarById(@PathVariable Long id) {
        Funcao updatedFuncao = funcaoService.ativarById(id);
        FuncaoOutput funcaoOutput = new FuncaoOutput(updatedFuncao);
        return ResponseEntity.ok(funcaoOutput);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FuncaoOutput> deactivateById(@PathVariable Long id) {
        Funcao deactivatedFuncao = funcaoService.deactivateById(id);
        FuncaoOutput funcaoOutput = new FuncaoOutput(deactivatedFuncao);
        return ResponseEntity.ok(funcaoOutput);
    }
}
