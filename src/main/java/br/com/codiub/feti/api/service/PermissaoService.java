package br.com.codiub.feti.api.service;

import br.com.codiub.feti.model.entity.Permissao;
import br.com.codiub.feti.model.entity.RoleTela;
import br.com.codiub.feti.model.input.PermissaoInput;
import br.com.codiub.feti.model.input.RoleInput;
import br.com.codiub.feti.model.input.RoleTelaInput;
import br.com.codiub.feti.repository.PermissaoRepository;
import br.com.codiub.feti.repository.RoleRepository;
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
public class PermissaoService {

    @Autowired
    private final PermissaoRepository permissaoRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Permissao findById(Long id) {
        return permissaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Permissao nao encontrada"));
    }

    public List<Permissao> listAll() {
        return permissaoRepository.findAll();
    }

    public Permissao save(PermissaoInput permissaoInput) {
        Permissao permissao = modelMapper.map(permissaoInput, Permissao.class);
        return permissaoRepository.save(permissao);
    }

    public Permissao updateById(Long id, PermissaoInput permissaoInput) {
        Permissao permissao = findById(id);
        modelMapper.map(permissaoInput, permissao);
        return permissaoRepository.save(permissao);
    }

    public void desactivateById(Long id) {
        Permissao permissao = findById(id);
        permissaoRepository.delete(permissao);
    }
}


