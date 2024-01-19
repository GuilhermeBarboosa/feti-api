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
@Table(name = "perguntas")
public class Pontuacao extends DefaultEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_pergunta")
    @SequenceGenerator(sequenceName = "id_seq_pergunta", allocationSize = 1, name = "id_seq_pergunta")
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "pergunta", nullable = false)
    private String pergunta;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "funcao", nullable = false)
    private Funcao funcao;
}