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
@Table(name = "alternativas")
public class Alternativa extends DefaultEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_alternativa")
    @SequenceGenerator(sequenceName = "id_seq_alternativa", allocationSize = 1, name = "id_seq_alternativa")
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "alternativa", nullable = false)
    private String alternativa;

    @NotNull
    @Column(name = "pontuacao", nullable = false)
    private Integer pontuacao;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pergunta", nullable = false)
    private Pergunta pergunta;
}