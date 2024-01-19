package br.com.codiub.feti.api.service;

import br.com.codiub.feti.model.entity.Edital;
import br.com.codiub.feti.model.input.EditalInput;
import br.com.codiub.feti.repository.EditalRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EditalService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EditalRepository editalRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private final RoleService roleService;

    public Edital save(EditalInput editalInput) {

        Edital edital = modelMapper.map(editalInput, Edital.class);
        return editalRepository.save(edital);
    }

    public List<Edital> listAll() {
        return editalRepository.findAll();
    }

    public Edital findById(Long id) {
        return editalRepository.findById(id).orElseThrow(() -> new RuntimeException("Edital não encontrada"));
    }

    public Edital updateById(Long id, EditalInput editalInput) {
        Edital edital = findById(id);
        edital.setEdital(editalInput.getEdital());
        return editalRepository.save(edital);
    }

    public Edital deactivateById(Long id) {
        Edital edital = findById(id);
        edital.setActived(false);
        return editalRepository.save(edital);
    }

    public List<Edital> listAllEditalDesactived() {
        return editalRepository.findAllEditalDesactived();
    }

    public Edital findByIdDesactived(Long id) {
        return editalRepository.findByIdDesactived(id).orElseThrow(() -> new RuntimeException("Edital não encontrada"));
    }

    public Edital ativarById(Long id) {
        Edital edital = findByIdDesactived(id);
        edital.setActived(true);
        return editalRepository.save(edital);
    }

}


