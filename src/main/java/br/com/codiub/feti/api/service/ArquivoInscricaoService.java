package br.com.codiub.feti.api.service;

import br.com.codiub.feti.model.entity.ArquivoInscricao;
import br.com.codiub.feti.model.input.ArquivoInscricaoInput;
import br.com.codiub.feti.repository.ArquivoInscricaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@RequiredArgsConstructor
public class ArquivoInscricaoService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ArquivoInscricaoRepository arquivoInscricaoRepository;
    @Autowired
    private InscricaoRespostaService inscricaoRespostaService;
    @Autowired
    private InscricaoService inscricaoService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private final RoleService roleService;
    @Value("${filerdiretorio}")
    private String filedir;
    private String caminhoArquivo;

    @PostConstruct
    public void init() {
        filedir = filedir.replaceAll("\"", "").replaceAll(";", "");
        caminhoArquivo = filedir + "arquivos/";
    }


    public List<ArquivoInscricao> save(List<ArquivoInscricaoInput> arquivoInscricaoInput, Long idInscricao) {
        List<ArquivoInscricao> responseArquivo = new ArrayList();
        for (ArquivoInscricaoInput arquivo : arquivoInscricaoInput) {
            ArquivoInscricao arquivoInscricao = modelMapper.map(arquivo, ArquivoInscricao.class);
            arquivoInscricao.setInscricao(inscricaoService.findById(idInscricao));
            arquivoInscricao.setCaminho_arquivo(
                    arquivo.getNome_arquivo()
                            .replaceAll("\\s", "")
                            .toLowerCase() +
                            "{id-" + idInscricao + "}"
            );
            responseArquivo.add(arquivoInscricaoRepository.save(arquivoInscricao));
        }

        return responseArquivo;
    }

    public void saveFile(List<MultipartFile> files, Long id, Long idInscricao) {
        for (MultipartFile file : files) {
            String nomeArquivoOriginal = file.getOriginalFilename();
            String nomeArquivoSemEspacos = nomeArquivoOriginal.replaceAll("\\s", "").toLowerCase();

            String caminhoCompleto = caminhoArquivo + nomeArquivoSemEspacos + "{id-" + idInscricao + "}.pdf";

            File fileEdital = new File(caminhoCompleto);

            // Certifique-se de que o diretório de destino existe
            if (!fileEdital.getParentFile().exists()) {
                fileEdital.getParentFile().mkdirs();
            }

            // Escrever os bytes do arquivo no arquivo de destino
            try (FileOutputStream fos = new FileOutputStream(fileEdital)) {
                fos.write(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
                // Lide com a exceção conforme necessário
                inscricaoRespostaService.delete(idInscricao);
                inscricaoService.delete(idInscricao);
            }
        }
    }


    public ResponseEntity<?> getFile(Long idInscricao, String nome) {
        try {
            Path caminhoDiretorio = Paths.get(caminhoArquivo);

            // Define o padrão de busca para o PDF específico
            String padraoBusca = String.format("%s{id-%d}.pdf", nome, idInscricao);
            // Busca o arquivo com base no padrão
            Path caminhoArquivoPDF = Files.walk(caminhoDiretorio)
                    .filter(path -> path.toFile().isFile() && path.getFileName().toString().startsWith(padraoBusca))
                    .findFirst()
                    .orElse(null);

            if (caminhoArquivoPDF != null) {
                // Criando um recurso a partir do arquivo PDF
                Resource resource = new UrlResource(caminhoArquivoPDF.toUri());

                // Configurando o cabeçalho da resposta
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + caminhoArquivoPDF.getFileName().toString());

                return ResponseEntity.ok()
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Trate a exceção conforme necessário
            return ResponseEntity.status(500).body(null);
        }
    }

    public void zipArquivosEncontrados(Path caminhoArquivoZip, Path caminhoDiretorio, String padraoBusca) throws IOException {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(caminhoArquivoZip.toFile()))) {
            Collection<File> arquivosEncontrados = FileUtils.listFiles(caminhoDiretorio.toFile(), new String[]{"pdf"}, true);

            for (File arquivo : arquivosEncontrados) {
                String entradaNome = caminhoDiretorio.relativize(arquivo.toPath()).toString();
                zipOutputStream.putNextEntry(new ZipEntry(entradaNome));

                try (InputStream inputStream = new FileInputStream(arquivo)) {
                    IOUtils.copy(inputStream, zipOutputStream);
                }

                zipOutputStream.closeEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void deleteById(Long id) {
        arquivoInscricaoRepository.deleteById(id);
    }

    public List<ArquivoInscricao> findByInscricao(Long inscricao) {
       return arquivoInscricaoRepository.findByInscricao(inscricao);
    }
}


