package com.seph_worker.worker.core.entity.Catalogos.Direcciones;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@Entity
@Table(name = "cat_localidad")
public class CatLocalidad {

    @Id
    @Column(name = "cve_loc", length = 2)
    private String cveLoc;

    @Column(name = "cve_ent", length = 2)
    private String cveEnt;

    @Column(name = "cve_mun", length = 2)
    private String cveMun;

    @Column(name = "nom_loc")
    private String nomLoc;

    @Column(name = "nom_mun")
    private String nomMun;

    @Column(name = "nom_ent")
    private String nomEnt;

}
