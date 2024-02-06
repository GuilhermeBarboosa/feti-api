package br.com.codiub.feti.api.service;

import br.com.codiub.feti.model.entity.Situacao;
import br.com.codiub.feti.repository.SituacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SituacaoService {
    @Autowired
    private final SituacaoRepository situacaoRepository;

    public Situacao findById(Long id) {
        return situacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Situacao não encontrada"));
    }

    public List<Situacao> listAllSituacao() {
        return situacaoRepository.findAll();
    }

}
