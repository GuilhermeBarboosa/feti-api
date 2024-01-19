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
@Table(name = "funcoes")
public class Funcao extends DefaultEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_funcao")
    @SequenceGenerator(sequenceName = "id_seq_funcao", allocationSize = 1, name = "id_seq_funcao")
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "funcao", nullable = false)
    private String funcao;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "edital", nullable = false)
    private Edital edital;
}