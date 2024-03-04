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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    public ResponseEntity<?> save(@Valid @RequestBody List<RoleTelaInput> roleTelaInput) {
        if(!Objects.requireNonNull(this.listAllRole(roleTelaInput.get(0).getRole()).getBody()).isEmpty()){
            this.roleTelaService.deleteByRole(roleTelaInput.get(0).getRole());
        }
        List<RoleTela> createdRoleTela = roleTelaService.save(roleTelaInput);
        List<RoleTelaOutput> responseDTOS = getRoleTelaOutputs(createdRoleTela);
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping
    public ResponseEntity<List<RoleTelaOutput>> listAll() {
        List<RoleTela> roleTelas = roleTelaService.listAll();
        List<RoleTelaOutput> responseDTOS = getRoleTelaOutputs(roleTelas);
        return ResponseEntity.ok(responseDTOS);
    }


    @GetMapping("/role/{id}")
    public ResponseEntity<List<RoleTelaOutput>> listAllRole(@PathVariable Long id) {
        List<RoleTela> roleTelas = roleTelaService.findByRole(id);
        List<RoleTelaOutput> responseDTOS = getRoleTelaOutputs(roleTelas);
        return ResponseEntity.ok(responseDTOS);
    }


    @GetMapping("/{id}")
    public ResponseEntity<RoleTelaOutput> getById(@PathVariable Long id) {
        RoleTela roleTela = roleTelaService.findById(id);
        RoleTelaOutput roleTelaOutput = new RoleTelaOutput(roleTela);
        return ResponseEntity.ok(roleTelaOutput);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody @Valid List<RoleTelaInput> roleTelaInput) {
        roleTelaService.deleteByRole(roleTelaInput.get(0).getRole());
        return ResponseEntity.ok(save(roleTelaInput));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deactivateById(@PathVariable Long id) {
        roleTelaService.deactivateById(id);
        return ResponseEntity.ok().build();
    }

    private static List<RoleTelaOutput> getRoleTelaOutputs(List<RoleTela> createdRoleTela) {
        List<RoleTelaOutput> responseDTOS = createdRoleTela.stream()
                .map(RoleTelaOutput::new)
                .collect(Collectors.toList());
        return responseDTOS;
    }
}
