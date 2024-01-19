package br.com.codiub.feti.model.defaults;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class DefaultEntity implements Serializable {
    @NotNull
    @Column(name = "actived")
    private Boolean actived;
    @NotNull
    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    @PrePersist
    public void onCreate() {
        this.actived = Boolean.TRUE;
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updated = LocalDateTime.now();
    }

}
