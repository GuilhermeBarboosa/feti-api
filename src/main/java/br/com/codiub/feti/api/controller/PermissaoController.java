package br.com.codiub.feti.api.controller;

import br.com.codiub.feti.api.service.PermissaoService;
import br.com.codiub.feti.model.entity.Permissao;
import br.com.codiub.feti.model.input.PermissaoInput;
import br.com.codiub.feti.model.output.PermissaoOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/permissao")
public class PermissaoController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private final PermissaoService permissaoService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody PermissaoInput permissaoInput) {
        Permissao createdPermissao = permissaoService.save(permissaoInput);
        PermissaoOutput permissaoOutput = new PermissaoOutput(createdPermissao);
        return ResponseEntity.ok(permissaoOutput);
    }

    @GetMapping
    public ResponseEntity<List<PermissaoOutput>> listAll() {
        List<Permissao> permissaos = permissaoService.listAll();
        List<PermissaoOutput> responseDTOS = permissaos.stream()
                .map(PermissaoOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody @Valid PermissaoInput permissaoInput) {
        Permissao updatedPermissao = permissaoService.updateById(id, permissaoInput);
        PermissaoOutput permissaoOutput = new PermissaoOutput(updatedPermissao);
        return ResponseEntity.ok(permissaoOutput);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deactivateById(@PathVariable Long id) {
        permissaoService.desactivateById(id);
        return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Permissao desativada com sucesso\"}");
    }


    @GetMapping("/{id}")
    public ResponseEntity<PermissaoOutput> getById(@PathVariable Long id) {
        Permissao permissao = permissaoService.findById(id);
        PermissaoOutput permissaoOutput = new PermissaoOutput(permissao);
        return ResponseEntity.ok(permissaoOutput);
    }

}
