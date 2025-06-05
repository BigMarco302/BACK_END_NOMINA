package com.seph_worker.worker.core.entity.CatEstadoCivil;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "cat_estado_civil")
public class CatEstadoCivil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "estado_civil", nullable = false)
    private String estadoCivil;

}
