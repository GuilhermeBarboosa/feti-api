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
@Table(name = "situacao")
public class Situacao extends DefaultEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_situacao")
    @SequenceGenerator(sequenceName = "id_seq_situacao", allocationSize = 1, name = "id_seq_situacao")
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "situacao", nullable = false)
    private String situacao;
}