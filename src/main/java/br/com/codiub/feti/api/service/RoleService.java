package br.com.codiub.feti.api.service;

import br.com.codiub.feti.model.entity.Role;
import br.com.codiub.feti.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    @Autowired
    private final RoleRepository roleRepository;

    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role não encontrada"));
    }

    public List<Role> listAllRole() {
        return roleRepository.findAll();
    }

}
