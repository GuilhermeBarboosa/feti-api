package br.com.codiub.feti.api.service;

import br.com.codiub.feti.model.entity.Funcao;
import br.com.codiub.feti.model.entity.Role;
import br.com.codiub.feti.model.input.RoleInput;
import br.com.codiub.feti.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role não encontrada"));
    }

    public List<Role> listAllRole() {
        return roleRepository.findAll();
    }

    public Role save(RoleInput roleInput) {
        Role role = modelMapper.map(roleInput, Role.class);
        roleInput.setRole(roleInput.getRole().toUpperCase());
        return roleRepository.save(role);
    }

    public Role updateById(Long id, RoleInput roleInput) {
        Role role = findById(id);
        modelMapper.map(roleInput, role);
        return roleRepository.save(role);
    }

    public void desactivateById(Long id) {
        Role role = findById(id);
        roleRepository.delete(role);
    }
}
