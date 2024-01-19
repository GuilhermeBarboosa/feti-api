package br.com.codiub.feti.api.controller;


import br.com.codiub.feti.api.service.*;
import br.com.codiub.feti.model.entity.Alternativa;
import br.com.codiub.feti.model.entity.Inscricao;
import br.com.codiub.feti.model.entity.InscricaoResposta;
import br.com.codiub.feti.model.entity.Pergunta;
import br.com.codiub.feti.model.input.AlternativaInput;
import br.com.codiub.feti.model.output.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/perguntaWithAlternativa")
public class PerguntaWithAlternativaController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private final AlternativaService alternativaService;

    @Autowired
    private final PerguntaService perguntaService;


    @Autowired
    private final InscricaoRespostaService inscricaoRespostaService;

    @Autowired
    private final InscricaoService inscricaoService;


    @GetMapping("/{idFuncao}")
    public ResponseEntity<List<PerguntaAlternativaOutput>> listAll(@PathVariable Long idFuncao) {
        List<Alternativa> alternativas = alternativaService.listAlternativasWithFuncao(idFuncao);
        List<Pergunta> perguntas = perguntaService.listPerguntasWithFuncao(idFuncao);
        List<PerguntaAlternativaOutput> listPerguntaWithAlternativasList = new ArrayList<>();

        for (Pergunta pergunta : perguntas) {
            PerguntaAlternativaOutput perguntaTemp = new PerguntaAlternativaOutput(pergunta);
            List<AlternativaOutput> listAlternativaTemp = new ArrayList<>();
            for (Alternativa alternativa : alternativas) {
                if (perguntaTemp.getIdPergunta() == alternativa.getPergunta().getId()) {
                    AlternativaOutput alternativaOutput = new AlternativaOutput(alternativa);
                    listAlternativaTemp.add(alternativaOutput);
                }
            }
            perguntaTemp.setListAlternativas(listAlternativaTemp);
            listPerguntaWithAlternativasList.add(perguntaTemp);
        }

        return ResponseEntity.ok(listPerguntaWithAlternativasList);
    }


}
