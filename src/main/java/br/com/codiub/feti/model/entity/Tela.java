package br.com.codiub.feti.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "telas")
public class Tela {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_tela")
    @SequenceGenerator(sequenceName = "id_seq_tela", allocationSize = 1, name = "id_seq_tela")
    private Long id;

    @Size(max = 60)
    @NotNull
    @Column(name = "identificador", nullable = false, length = 80)
    private String identificador;

    @Size(max = 60)
    @NotNull
    @Column(name = "descricao", nullable = false, length = 80)
    private String descricao;

    @Size(max = 60)
    @NotNull
    @Column(name = "unica", nullable = false, length = 80)
    private Boolean unica;


}