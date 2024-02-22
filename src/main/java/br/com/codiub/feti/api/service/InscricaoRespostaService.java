package br.com.codiub.feti.api.service;

import br.com.codiub.feti.model.entity.Inscricao;
import br.com.codiub.feti.model.entity.InscricaoResposta;
import br.com.codiub.feti.model.input.PerguntaRespostaInput;
import br.com.codiub.feti.repository.InscricaoRespostaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InscricaoRespostaService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private InscricaoRespostaRepository InscricaoRespostaRepository;
    @Autowired
    private PerguntaService perguntaService;
    @Autowired
    private AlternativaService alternativaService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private final RoleService roleService;

    public List<InscricaoResposta> save(List<PerguntaRespostaInput> InscricaoRespostaInput, Inscricao inscricao) {
        List<InscricaoResposta> responseInscricaoResposta = new ArrayList<>();
        InscricaoRespostaInput.forEach(InscricaoResposta -> {
            InscricaoResposta inscricaoRespostaCreated = modelMapper.map(InscricaoResposta, InscricaoResposta.class);
            inscricaoRespostaCreated.setInscricao(inscricao);
            inscricaoRespostaCreated.setPergunta(perguntaService.findById(InscricaoResposta.getPergunta()));
            inscricaoRespostaCreated.setAlternativa(alternativaService.findById(InscricaoResposta.getAlternativa()));
            this.InscricaoRespostaRepository.save(inscricaoRespostaCreated);
            responseInscricaoResposta.add(inscricaoRespostaCreated);
        });
        return responseInscricaoResposta;
    }

    public List<InscricaoResposta> listAll() {
        return InscricaoRespostaRepository.findAll();
    }

    public InscricaoResposta findById(Long id) {
        return InscricaoRespostaRepository.findById(id).orElseThrow(() -> new RuntimeException("InscricaoResposta não encontrada"));
    }

    public InscricaoResposta updateById(Long id, PerguntaRespostaInput InscricaoRespostaInput) {
        InscricaoResposta InscricaoResposta = findById(id);
//        InscricaoResposta.setPergunta(perguntaService.findById(InscricaoRespostaInput.getPergunta()));
//        InscricaoResposta.setAlternativa(alternativaService.findById(InscricaoRespostaInput.getAlternativa()));
//        InscricaoResposta.setEdital(editalService.findById(InscricaoRespostaInput.getEdital()));
//        InscricaoResposta.setFuncao(funcaoService.findById(InscricaoRespostaInput.getFuncao()));
//        InscricaoResposta.setUsuario(userService.findById(InscricaoRespostaInput.getUsuario()));
        return InscricaoRespostaRepository.save(InscricaoResposta);
    }

    public InscricaoResposta deactivateById(Long id) {
        InscricaoResposta InscricaoResposta = findById(id);
        InscricaoResposta.setActived(false);
        return InscricaoRespostaRepository.save(InscricaoResposta);
    }

    public List<InscricaoResposta> listAllInscricaoRespostaDesactived() {
        return InscricaoRespostaRepository.findAllInscricaoRespostaDesactived();
    }

    public InscricaoResposta findByIdDesactived(Long id) {
        return InscricaoRespostaRepository.findByIdDesactived(id).orElseThrow(() -> new RuntimeException("InscricaoResposta não encontrada"));
    }

    public InscricaoResposta ativarById(Long id) {
        InscricaoResposta InscricaoResposta = findByIdDesactived(id);
        InscricaoResposta.setActived(true);
        return InscricaoRespostaRepository.save(InscricaoResposta);
    }


    public List<InscricaoResposta> findByInscricao(Long id) {
        return InscricaoRespostaRepository.findByInscricao(id);
    }

    public void delete(Long idInscricao) {
        InscricaoRespostaRepository.deleteByInscricao(idInscricao);
    }
}


