package br.com.codiub.feti.model.input;

import br.com.codiub.feti.model.entity.Pergunta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AlternativaInput {

   private String alternativa;

   private Integer pontuacao;

   private Long pergunta;
}
