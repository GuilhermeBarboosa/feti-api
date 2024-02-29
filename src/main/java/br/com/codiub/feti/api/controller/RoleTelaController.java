package br.com.codiub.feti.api.controller;


import br.com.codiub.feti.api.service.RoleTelaService;
import br.com.codiub.feti.api.service.InscricaoService;
import br.com.codiub.feti.model.entity.RoleTela;
import br.com.codiub.feti.model.entity.Inscricao;
import br.com.codiub.feti.model.input.RoleTelaInput;
import br.com.codiub.feti.model.input.VerifyInput;
import br.com.codiub.feti.model.output.RoleTelaOutput;
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
@RequestMapping("/roleTela")
public class RoleTelaController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private final RoleTelaService roleTelaService;

    @Autowired
    private final InscricaoService inscricaoService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody RoleTelaInput roleTelaInput) {
        RoleTela createdRoleTela = roleTelaService.save(roleTelaInput);
        RoleTelaOutput roleTelaOutput = new RoleTelaOutput(createdRoleTela);
        return ResponseEntity.ok(roleTelaOutput);
    }

    @GetMapping
    public ResponseEntity<List<RoleTelaOutput>> listAll() {
        List<RoleTela> roleTelas = roleTelaService.listAll();
        List<RoleTelaOutput> responseDTOS = roleTelas.stream()
                .map(RoleTelaOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }


    @GetMapping("/role/{id}")
    public ResponseEntity<List<RoleTelaOutput>> listAllRole(@PathVariable Long id) {
        List<RoleTela> roleTelas = roleTelaService.findByRole(id);
        List<RoleTelaOutput> responseDTOS = roleTelas.stream()
                .map(RoleTelaOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }


    @GetMapping("/{id}")
    public ResponseEntity<RoleTelaOutput> getById(@PathVariable Long id) {
        RoleTela roleTela = roleTelaService.findById(id);
        RoleTelaOutput roleTelaOutput = new RoleTelaOutput(roleTela);
        return ResponseEntity.ok(roleTelaOutput);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody @Valid RoleTelaInput roleTelaInput) {
        RoleTela updatedRoleTela = roleTelaService.updateById(id, roleTelaInput);
        RoleTelaOutput roleTelaOutput = new RoleTelaOutput(updatedRoleTela);
        return ResponseEntity.ok(roleTelaOutput);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deactivateById(@PathVariable Long id) {
        roleTelaService.deactivateById(id);
        return ResponseEntity.ok().build();
    }
}
