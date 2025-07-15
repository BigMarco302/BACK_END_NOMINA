package com.seph_worker.worker.core.entity.Catalogos.Direcciones;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@Entity
@Table(name = "cat_entidad")
public class CatEntidad{

    @Id
    @Column(name = "cve_ent", length = 2)
    private String cveEnt;

}
