package br.com.codiub.feti.api.service;

import br.com.codiub.feti.model.entity.Inscricao;
import br.com.codiub.feti.model.input.EditInscricaoInput;
import br.com.codiub.feti.model.input.InscricaoInput;
import br.com.codiub.feti.model.input.PerguntaRespostaInput;
import br.com.codiub.feti.model.input.VerifyInput;
import br.com.codiub.feti.repository.InscricaoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InscricaoService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private InscricaoRepository inscricaoRepository;
    @Autowired
    private PerguntaService perguntaService;
    @Autowired
    private AlternativaService alternativaService;
    @Autowired
    private UserService userService;
    @Autowired
    private EditalService editalService;
    @Autowired
    private SituacaoService situacaoService;
    @Autowired
    private FuncaoService funcaoService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private final RoleService roleService;

    public Inscricao save(InscricaoInput inscricaoInput) {
        List<PerguntaRespostaInput> perguntaRespostaInput = inscricaoInput.getPerguntaResposta();
        int totalPontos = 0;
        for (PerguntaRespostaInput p : perguntaRespostaInput) {
            totalPontos += p.getPontuacao();
        }

        Inscricao inscricaoCreated = modelMapper.map(inscricaoInput, Inscricao.class);
        inscricaoCreated.setSituacao(situacaoService.findById(inscricaoInput.getSituacao()));
        inscricaoCreated.setEdital(editalService.findById(inscricaoInput.getEdital()));
        inscricaoCreated.setFuncao(funcaoService.findById(inscricaoInput.getFuncao()));
        inscricaoCreated.setUsuario(userService.findById(inscricaoInput.getUsuario()));
        inscricaoCreated.setPontuacao((long) totalPontos);

        return this.inscricaoRepository.save(inscricaoCreated);
    }

    public List<Inscricao> listAll() {
        return inscricaoRepository.findAll();
    }

    public Inscricao findById(Long id) {
        return inscricaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Inscricao não encontrada"));
    }

    public Inscricao updateById(Long id, EditInscricaoInput inscricaoInput) {
        Inscricao inscricao = findById(id);
        inscricao.setPontuacao(inscricaoInput.getPontuacao());
        inscricao.setSituacao(situacaoService.findById(inscricaoInput.getSituacao()));

        return inscricaoRepository.save(inscricao);
    }

    public Inscricao deactivateById(Long id) {
        Inscricao inscricao = findById(id);
        inscricao.setActived(false);
        return inscricaoRepository.save(inscricao);
    }

    public List<Inscricao> listAllInscricaoDesactived() {
        return inscricaoRepository.findAllInscricaoDesactived();
    }

    public Inscricao findByIdDesactived(Long id) {
        return inscricaoRepository.findByIdDesactived(id).orElseThrow(() -> new RuntimeException("Inscricao não encontrada"));
    }

    public Inscricao ativarById(Long id) {
        Inscricao inscricao = findByIdDesactived(id);
        inscricao.setActived(true);
        return inscricaoRepository.save(inscricao);
    }

    public Inscricao findByUser(Long idUser) {
        return inscricaoRepository.findByUser(idUser).orElseThrow(() -> new RuntimeException("Inscricao não encontrada"));
    }

    public Optional<Inscricao> getVerifyEdital(VerifyInput verifyInput) {
        return inscricaoRepository.findEditalInscricao(verifyInput.getIdEdital(),
                verifyInput.getIdFuncao(),
                verifyInput.getIdUser());
    }

    public List<Inscricao> findByAllUser(Long id) {
        return inscricaoRepository.findByAllUser(id);
    }

    public Inscricao updateByPontuacao(Long id, Long pontuacao) {
        Inscricao inscricao = findById(id);
        inscricao.setPontuacao(pontuacao);

        return inscricaoRepository.save(inscricao);
    }
}


