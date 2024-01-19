package br.com.codiub.feti.model.defaults;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public abstract class DefaultEntityDTO implements Serializable {
    private Boolean actived;
    private LocalDateTime updated;
    private LocalDateTime created;

}
