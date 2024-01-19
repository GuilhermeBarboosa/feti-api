package br.com.codiub.feti.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VerifyInput {
   private Long idEdital;

   private Long idUser;

   private Long idFuncao;
}
