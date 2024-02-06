package br.com.codiub.feti.model.input;

import br.com.codiub.feti.model.entity.InscricaoResposta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InscricaoInput {

    private Long edital;
    private Long funcao;
    private Long usuario;
    private Long situacao = 1L; //DE ACORDO COM O BANCO
    private List<PerguntaRespostaInput> perguntaResposta;
}
