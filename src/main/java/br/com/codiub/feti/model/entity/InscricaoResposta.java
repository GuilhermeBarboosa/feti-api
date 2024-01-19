package br.com.codiub.feti.model.entity;

import br.com.codiub.feti.model.defaults.DefaultEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Table(name = "inscricao_respostas")
public class InscricaoResposta extends DefaultEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_inscricao_resposta")
    @SequenceGenerator(sequenceName = "id_seq_inscricao_resposta", allocationSize = 1, name = "id_seq_inscricao_resposta")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pergunta", nullable = false)
    private Pergunta pergunta;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "alternativa", nullable = false)
    private Alternativa alternativa;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "inscricao", nullable = false)
    private Inscricao inscricao;
}