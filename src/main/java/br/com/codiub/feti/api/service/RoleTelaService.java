package br.com.codiub.feti.api.service;

import br.com.codiub.feti.model.entity.RoleTela;
import br.com.codiub.feti.model.input.RoleTelaInput;
import br.com.codiub.feti.repository.RoleTelaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleTelaService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RoleTelaRepository roleTelaRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private final TelaService telaService;
    @Autowired
    private final RoleService roleService;


    public RoleTela save(RoleTelaInput roleTelaInput) {

        RoleTela roleTela = modelMapper.map(roleTelaInput, RoleTela.class);
        roleTela.setTela(telaService.findById(roleTelaInput.getTela()));
        roleTela.setRole(roleService.findById(roleTelaInput.getRole()));
        return roleTelaRepository.save(roleTela);
    }

    public List<RoleTela> listAll() {
        return roleTelaRepository.findAll();
    }

    public RoleTela findById(Long id) {
        return roleTelaRepository.findById(id).orElseThrow(() -> new RuntimeException("RoleTela não encontrada"));
    }

    public RoleTela updateById(Long id, RoleTelaInput roleTelaInput) {
        RoleTela roleTela = findById(id);
        roleTela.setTela(telaService.findById(roleTelaInput.getTela()));
        roleTela.setRole(roleService.findById(roleTelaInput.getRole()));
        return roleTelaRepository.save(roleTela);
    }

    public void deactivateById(Long id) {
        RoleTela roleTela = findById(id);
        roleTelaRepository.delete(roleTela);
    }

    public List<RoleTela> findByRole(Long id) {
        return roleTelaRepository.findByRole(id);
    }
}


