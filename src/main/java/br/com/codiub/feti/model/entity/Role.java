package br.com.codiub.feti.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_role")
    @SequenceGenerator(sequenceName = "id_seq_role", allocationSize = 1, name = "id_seq_role")
    private Long id;

    @Size(max = 60)
    @NotNull
    @Column(name = "role", nullable = false, length = 60)
    private String role;

}