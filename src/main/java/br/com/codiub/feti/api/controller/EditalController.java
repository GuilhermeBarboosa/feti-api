package br.com.codiub.feti.api.controller;


import br.com.codiub.feti.api.service.EditalService;
import br.com.codiub.feti.exception.ApiException;
import br.com.codiub.feti.model.entity.Edital;
import br.com.codiub.feti.model.input.EditalInput;
import br.com.codiub.feti.model.input.VerifyInput;
import br.com.codiub.feti.model.output.EditalOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/edital")
public class EditalController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private final EditalService editalService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody EditalInput editalInput) {
        if (this.editalService.findByEdital(editalInput.getEdital()).isPresent()) {
            return ResponseEntity.badRequest().body(new Exception("Edital já cadastrado"));
        }else{
            Edital createdEdital = editalService.save(editalInput);
            EditalOutput editalOutput = new EditalOutput(createdEdital);
            return ResponseEntity.ok(editalOutput);
        }
    }

    @PostMapping("/uploadFile/{id}")
    public ResponseEntity<?> saveFile(@RequestParam("file") MultipartFile file, @PathVariable Long id) {
        this.editalService.saveFile(file, id);
        return ResponseEntity.ok("Arquivo inserido com sucesso");
    }

    @GetMapping
    public ResponseEntity<List<EditalOutput>> listAll() {
        List<Edital> editals = editalService.listAll();
        List<EditalOutput> responseDTOS = editals.stream()
                .map(EditalOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/desativado")
    public ResponseEntity<List<EditalOutput>> listAllEdital() {
        List<Edital> editals = editalService.listAllEditalDesactived();
        List<EditalOutput> responseDTOS = editals.stream()
                .map(EditalOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EditalOutput> getById(@PathVariable Long id) {
        Edital edital = editalService.findById(id);
        EditalOutput editalOutput = new EditalOutput(edital);
        return ResponseEntity.ok(editalOutput);
    }

    @GetMapping("/downloadEdital/{id}")
    public void getByDownloadEdital(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Edital edital = editalService.findById(id);
        EditalOutput editalOutput = new EditalOutput(edital);
        File arquivo = this.editalService.procurarEditalArquivo(edital.getArquivo()); // Seu método para encontrar o arquivo com o CPF

        if (arquivo.isFile()) {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + arquivo.getName() + "\"");

            try (InputStream inputStream = Files.newInputStream(arquivo.toPath());
                 OutputStream outputStream = response.getOutputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            response.setContentType("text/plain");
            response.getWriter().write("Arquivo não encontrado");
        }
    }

    @GetMapping("/desativado/{id}")
    public ResponseEntity<EditalOutput> getByIdDesactived(@PathVariable Long id) {
        Edital edital = editalService.findByIdDesactived(id);
        EditalOutput editalOutput = new EditalOutput(edital);
        return ResponseEntity.ok(editalOutput);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody @Valid EditalInput editalInput) {
        Edital updatedEdital = editalService.updateById(id, editalInput);
        EditalOutput editalOutput = new EditalOutput(updatedEdital);
        return ResponseEntity.ok(editalOutput);
    }

    @PutMapping("/ativar/{id}")
    public ResponseEntity<?> ativarById(@PathVariable Long id) {
        Edital updatedEdital = editalService.ativarById(id);
        EditalOutput editalOutput = new EditalOutput(updatedEdital);
        return ResponseEntity.ok(editalOutput);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EditalOutput> deactivateById(@PathVariable Long id) {
        Edital deactivatedEdital = editalService.deactivateById(id);
        EditalOutput editalOutput = new EditalOutput(deactivatedEdital);
        return ResponseEntity.ok(editalOutput);
    }
}
