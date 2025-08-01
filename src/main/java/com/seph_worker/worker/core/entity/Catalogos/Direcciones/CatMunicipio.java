package com.seph_worker.worker.core.entity.Catalogos.Direcciones;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@Entity
@Table(name = "cat_municipio")
public class CatMunicipio {

    @Id
    @Column(name = "cve_mun", length = 2)
    private String cveMun;

    @Column(name = "cve_ent", length = 2)
    private String cveEnt;

    @Column(name = "nom_ent")
    private String nomEnt;

    @Column(name = "nom_mun" )
    private String nomMun;

}
