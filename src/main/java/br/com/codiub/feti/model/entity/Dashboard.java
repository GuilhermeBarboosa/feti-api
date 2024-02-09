package br.com.codiub.feti.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class Dashboard {

    private String edital;
    private Long qtdEdital;

}