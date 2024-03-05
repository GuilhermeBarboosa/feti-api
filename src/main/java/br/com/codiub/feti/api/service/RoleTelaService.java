package br.com.codiub.feti.api.service;

import br.com.codiub.feti.model.entity.RoleTela;
import br.com.codiub.feti.model.input.RoleTelaInput;
import br.com.codiub.feti.repository.RoleTelaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    private final PermissaoService permissaoService;


    public List<RoleTela> save(List<RoleTelaInput> roleTelaInput) {
        List<RoleTela> roleTelas = new ArrayList<>();
        for (RoleTelaInput roleTela : roleTelaInput) {
            Optional<RoleTela> roleTelaResponse = roleTelaRepository.findByRoleByTela(roleTela.getRole(), roleTela.getTela());
            if(!roleTelaResponse.isPresent()){
                RoleTela roleTelaEntity = modelMapper.map(roleTela, RoleTela.class);
                roleTelaEntity.setTela(telaService.findById(roleTela.getTela()));
                roleTelaEntity.setRole(roleService.findById(roleTela.getRole()));
                roleTelaEntity.setPermissao(permissaoService.findById(roleTela.getPermissao()));
                roleTelas.add(roleTelaRepository.save(roleTelaEntity));
            }
        }
        return roleTelas;
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
        roleTela.setPermissao(permissaoService.findById(roleTelaInput.getPermissao()));
        return roleTelaRepository.save(roleTela);
    }

    public void deactivateById(Long id) {
        RoleTela roleTela = findById(id);
        roleTelaRepository.delete(roleTela);
    }

    public List<RoleTela> findByRole(Long id) {
        return roleTelaRepository.findByRole(id);
    }

    public void deleteByRole(Long role) {
        roleTelaRepository.deleteByRole(role);
    }
}


