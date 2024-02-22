package br.com.codiub.feti.api.controller;


import br.com.codiub.feti.api.service.ArquivoInscricaoService;
import br.com.codiub.feti.model.entity.ArquivoInscricao;
import br.com.codiub.feti.model.entity.Funcao;
import br.com.codiub.feti.model.entity.Inscricao;
import br.com.codiub.feti.model.input.ArquivoInscricaoInput;
import br.com.codiub.feti.model.output.ArquivoInscricaoOutput;
import br.com.codiub.feti.model.output.FuncaoOutput;
import br.com.codiub.feti.model.output.InscricaoOutput;
import jdk.internal.loader.Resource;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.validation.Path;
import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/arquivo_inscricao")
public class ArquivoInscricaoController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private final ArquivoInscricaoService arquivoInscricaoService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody List<ArquivoInscricaoInput> arquivoInscricaoInput) {
        List<ArquivoInscricao> createdArquivoInscricao = arquivoInscricaoService.save(arquivoInscricaoInput);
        List<ArquivoInscricaoOutput> responseDTOS = createdArquivoInscricao.stream()
                .map(ArquivoInscricaoOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);

    }

    @GetMapping("/inscricao/{idInscricao}")
    public ResponseEntity<List<ArquivoInscricaoOutput>> getById(@PathVariable Long idInscricao) {
        List<ArquivoInscricao> arquivos = arquivoInscricaoService.findByInscricao(idInscricao);
        List<ArquivoInscricaoOutput> responseDTOS = arquivos.stream()
                .map(ArquivoInscricaoOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }


    @PostMapping("/uploadFile/{id}/{idInscricao}")
    public ResponseEntity<?> saveArquivo(@RequestParam("files") List<MultipartFile> file, @PathVariable Long id, @PathVariable Long idInscricao) {
        try {
            this.arquivoInscricaoService.saveFile(file, id, idInscricao);
            return ResponseEntity.ok("Arquivo inserido com sucesso");
        } catch (Exception e) {
            if (e instanceof MissingServletRequestPartException) {
                // Tratar a exceção específica
                this.arquivoInscricaoService.deleteById(id);
                return ResponseEntity.badRequest().body(new Exception("Arquivo não encontrado na requisição"));
            } else {
                // Tratar outras exceções
                this.arquivoInscricaoService.deleteById(id);
                return ResponseEntity.badRequest().body(new Exception("Erro ao salvar arquivo"));
            }
        }
    }

    @GetMapping("/findFile/{idInscricao}/nome/{nome}")
    public ResponseEntity<?> buscarArquivosPorIdInscricao(@PathVariable Long idInscricao, @PathVariable String nome) {
        return this.arquivoInscricaoService.getFile(idInscricao, nome);
    }

}
