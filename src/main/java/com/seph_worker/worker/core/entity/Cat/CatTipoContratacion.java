package com.seph_worker.worker.core.entity.Cat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "cat_tipo_contratacion")
public class CatTipoContratacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "tipo_contratacion", nullable = false)
    private String tipoContratacion;
}
