package com.seph_worker.worker.core.entity.Cat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@Entity
@Table(name = "cat_municipio")
public class CatMunicipio {

    @Id
    @Column(name = "cve_mun", length = 2)
    private String cveMun;

}
