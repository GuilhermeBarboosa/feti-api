package br.com.codiub.feti.api.service;

import br.com.codiub.feti.model.entity.Funcao;
import br.com.codiub.feti.model.input.FuncaoInput;
import br.com.codiub.feti.repository.FuncaoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuncaoService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private FuncaoRepository funcaoRepository;
    @Autowired
    private EditalService editalService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private final RoleService roleService;

    public Funcao save(FuncaoInput funcaoInput) {

        Funcao funcao = modelMapper.map(funcaoInput, Funcao.class);
        funcao.setEdital(editalService.findById(funcaoInput.getEdital()));
        return funcaoRepository.save(funcao);
    }

    public List<Funcao> listAll() {
        return funcaoRepository.findAll();
    }

    public Funcao findById(Long id) {
        return funcaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Funcao não encontrada"));
    }

    public Funcao updateById(Long id, FuncaoInput funcaoInput) {
        Funcao funcao = findById(id);
        funcao.setFuncao(funcaoInput.getFuncao());
        funcao.setEdital(editalService.findById(funcaoInput.getEdital()));
        return funcaoRepository.save(funcao);
    }

    public Funcao deactivateById(Long id) {
        Funcao funcao = findById(id);
        funcao.setActived(false);
        return funcaoRepository.save(funcao);
    }

    public List<Funcao> listAllFuncaoDesactived() {
        return funcaoRepository.findAllFuncaoDesactived();
    }

    public Funcao findByIdDesactived(Long id) {
        return funcaoRepository.findByIdDesactived(id).orElseThrow(() -> new RuntimeException("Funcao não encontrada"));
    }

    public Funcao ativarById(Long id) {
        Funcao funcao = findByIdDesactived(id);
        funcao.setActived(true);
        return funcaoRepository.save(funcao);
    }

    public List<Funcao> findByIdEdital(Long idEdital) {
        return funcaoRepository.findByIdEdital(idEdital);
    }
}


