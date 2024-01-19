package br.com.codiub.feti.api.service;

import br.com.codiub.feti.exception.SenhaInvalidaException;
import br.com.codiub.feti.model.entity.Role;
import br.com.codiub.feti.model.entity.Pergunta;
import br.com.codiub.feti.model.input.PerguntaInput;
import br.com.codiub.feti.repository.PerguntaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PerguntaService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PerguntaRepository perguntaRepository;
    @Autowired
    private FuncaoService funcaoService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private final RoleService roleService;

    public Pergunta save(PerguntaInput perguntaInput) {
        Pergunta pergunta = modelMapper.map(perguntaInput, Pergunta.class);
        pergunta.setFuncao(funcaoService.findById(perguntaInput.getFuncao()));
        return perguntaRepository.save(pergunta);
    }

    public List<Pergunta> listAll() {
        return perguntaRepository.findAll();
    }

    public Pergunta findById(Long id) {
        return perguntaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pergunta não encontrada"));
    }

    public Pergunta updateById(Long id, PerguntaInput perguntaInput) {
        Pergunta pergunta = findById(id);
        pergunta.setPergunta(perguntaInput.getPergunta());
        pergunta.setFuncao(funcaoService.findById(perguntaInput.getFuncao()));
        return perguntaRepository.save(pergunta);
    }

    public Pergunta deactivateById(Long id) {
        Pergunta pergunta = findById(id);
        pergunta.setActived(false);
        return perguntaRepository.save(pergunta);
    }

    public List<Pergunta> listAllPerguntaDesactived() {
        return perguntaRepository.findAllPerguntaDesactived();
    }

    public Pergunta findByIdDesactived(Long id) {
        return perguntaRepository.findByIdDesactived(id).orElseThrow(() -> new RuntimeException("Pergunta não encontrada"));
    }

    public Pergunta ativarById(Long id) {
        Pergunta pergunta = findByIdDesactived(id);
        pergunta.setActived(true);
        return perguntaRepository.save(pergunta);
    }

    public List<Pergunta> listPerguntasWithFuncao(Long idFuncao) {
        return perguntaRepository.listPerguntasWithFuncao(idFuncao);
    }
}


