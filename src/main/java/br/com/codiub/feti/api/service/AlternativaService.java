package br.com.codiub.feti.api.service;

import br.com.codiub.feti.model.entity.Alternativa;
import br.com.codiub.feti.model.entity.Pergunta;
import br.com.codiub.feti.model.input.AlternativaInput;
import br.com.codiub.feti.model.output.PerguntaAlternativaOutput;
import br.com.codiub.feti.model.output.PerguntaOutput;
import br.com.codiub.feti.repository.AlternativaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlternativaService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AlternativaRepository alternativaRepository;
    @Autowired
    private PerguntaService perguntaService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private final RoleService roleService;

    public Alternativa save(AlternativaInput alternativaInput) {

        Alternativa alternativa = modelMapper.map(alternativaInput, Alternativa.class);
        alternativa.setPergunta(perguntaService.findById(alternativaInput.getPergunta()));
        return alternativaRepository.save(alternativa);
    }

    public List<Alternativa> listAll() {
        return alternativaRepository.findAll();
    }

    public Alternativa findById(Long id) {
        return alternativaRepository.findById(id).orElseThrow(() -> new RuntimeException("Alternativa não encontrada"));
    }

    public Alternativa updateById(Long id, AlternativaInput alternativaInput) {
        Alternativa alternativa = findById(id);
        alternativa.setAlternativa(alternativaInput.getAlternativa());
        alternativa.setPergunta(perguntaService.findById(alternativaInput.getPergunta()));
        alternativa.setPontuacao(alternativaInput.getPontuacao());
        return alternativaRepository.save(alternativa);
    }

    public Alternativa deactivateById(Long id) {
        Alternativa alternativa = findById(id);
        alternativa.setActived(false);
        return alternativaRepository.save(alternativa);
    }

    public List<Alternativa> listAllAlternativaDesactived() {
        return alternativaRepository.findAllAlternativaDesactived();
    }

    public Alternativa findByIdDesactived(Long id) {
        return alternativaRepository.findByIdDesactived(id).orElseThrow(() -> new RuntimeException("Alternativa não encontrada"));
    }

    public Alternativa ativarById(Long id) {
        Alternativa alternativa = findByIdDesactived(id);
        alternativa.setActived(true);
        return alternativaRepository.save(alternativa);
    }


    public List<Alternativa> listAlternativasWithFuncao(Long idFuncao) {
        return alternativaRepository.findAlternativasWithFuncao(idFuncao);
    }

    public List<Alternativa> getAlternativaByFuncao(Long id) {
        return alternativaRepository.getAlternativaByPergunta(id);
    }
}


