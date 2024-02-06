package br.com.codiub.feti.api.service;

import br.com.codiub.feti.exception.ApiException;
import br.com.codiub.feti.exception.ErrorResponse;
import br.com.codiub.feti.model.entity.Edital;
import br.com.codiub.feti.model.input.EditalInput;
import br.com.codiub.feti.repository.EditalRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.utils.IOUtils;
import org.hibernate.boot.archive.spi.ArchiveEntry;
import org.hibernate.boot.archive.spi.ArchiveException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class EditalService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EditalRepository editalRepository;
    @Value("${filerdiretorio}")
    private String filedir;
    private String caminhoEdital;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private final RoleService roleService;

    @PostConstruct
    public void init() {
        filedir = filedir.replaceAll("\"", "").replaceAll(";", "");
        caminhoEdital = filedir + "edital/";
    }

    public Edital save(EditalInput editalInput) {
        Edital edital = modelMapper.map(editalInput, Edital.class);
        edital.setArquivo(editalInput.getEdital());
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
        edital.setData_inicio(editalInput.getData_inicio());
        edital.setData_fim(editalInput.getData_fim());
        edital.setQtd_vagas(editalInput.getQtd_vagas());
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

    public File procurarEditalArquivo(String nomeEdital) {
        File diretorio = new File(caminhoEdital);
        List<File> arquivosEncontrados = new ArrayList<>();

        File[] arquivos = diretorio.listFiles();

        if (arquivos != null) {
            for (File arquivo : arquivos) {
                if (arquivo.isFile()) {
                    String nomeArquivo = arquivo.getName();

                    String nomeArquivoSemExtensao = nomeArquivo.substring(0, nomeArquivo.lastIndexOf('.'));

                    if (nomeArquivoSemExtensao.equals(nomeEdital)) {
                        arquivosEncontrados.add(arquivo);
                    }
                }
            }

            if (!arquivosEncontrados.isEmpty()) {
                // Retornar o primeiro arquivo encontrado
                return arquivosEncontrados.get(0);
            } else {
                throw new RuntimeException("Arquivo não encontrado para o edital: " + nomeEdital);
            }
        } else {
            throw new RuntimeException("Diretório não encontrado");
        }
    }


    public void saveFile(MultipartFile file, Long id) {
        Edital edital = findById(id);
        String caminhoCompleto = caminhoEdital + edital.getEdital() + ".pdf";

        File fileEdital = new File(caminhoCompleto);

        // Certifique-se de que o diretório de destino existe
        if (!fileEdital.getParentFile().exists()) {
            fileEdital.getParentFile().mkdirs();
        }

        // Escrever os bytes do relatório no arquivo
        try (FileOutputStream fos = new FileOutputStream(fileEdital)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            this.editalRepository.delete(edital);
        }
    }

    public Optional<Edital> findByEdital(String edital) {
       return this.editalRepository.findByEdital(edital);
    }
}


