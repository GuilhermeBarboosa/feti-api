package br.com.codiub.feti.api.service;

import br.com.codiub.feti.model.entity.Tela;
import br.com.codiub.feti.repository.RoleRepository;
import br.com.codiub.feti.repository.TelaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TelaService {
    @Autowired
    private final TelaRepository telaRepository;

    public Tela findById(Long id) {
        return telaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tela não encontrada"));
    }

    public List<Tela> listAllTela() {
        return telaRepository.findAll();
    }

}
