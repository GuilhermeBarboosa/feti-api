package br.com.codiub.feti.api.controller;

import br.com.codiub.feti.api.service.TelaService;
import br.com.codiub.feti.model.entity.Tela;
import br.com.codiub.feti.model.output.TelaOutput;
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
@RequestMapping("/tela")
public class TelaController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private final TelaService telaService;


    @GetMapping
    public ResponseEntity<List<TelaOutput>> listAll() {
        List<Tela> telas = telaService.listAllTela();
        List<TelaOutput> responseDTOS = telas.stream()
                .map(TelaOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TelaOutput> getById(@PathVariable Long id) {
        Tela tela = telaService.findById(id);
        TelaOutput telaOutput = new TelaOutput(tela);
        return ResponseEntity.ok(telaOutput);
    }

}
