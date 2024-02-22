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
@Table(name = "arquivos_inscricao")
public class ArquivoInscricao extends DefaultEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_arquivos_inscricao")
    @SequenceGenerator(sequenceName = "id_seq_arquivos_inscricao", allocationSize = 1, name = "id_seq_arquivos_inscricao")
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "caminho_arquivo", nullable = false)
    private String caminho_arquivo;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "inscricao", nullable = false)
    private Inscricao inscricao;
}