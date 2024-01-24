package br.com.codiub.feti.model.entity;

import br.com.codiub.feti.model.defaults.DefaultEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Table(name = "editais")
public class Edital extends DefaultEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_funcao")
    @SequenceGenerator(sequenceName = "id_seq_funcao", allocationSize = 1, name = "id_seq_funcao")
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "edital", nullable = false)
    private String edital;

    @NotNull
    @Column(name = "data_inicio", nullable = false)
    private Date data_inicio;

    @NotNull
    @Column(name = "data_fim", nullable = false)
    private Date data_fim;

    @NotNull
    @Column(name = "qtd_vagas", nullable = false)
    private Integer qtd_vagas;

    @Size(max = 255)
    @Column(name = "arquivo", nullable = true)
    private String arquivo;

}