package br.com.codiub.feti.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PerguntaRespostaInput {
      private Long pergunta;
      private Long alternativa;
      private Long pontuacao;
}
