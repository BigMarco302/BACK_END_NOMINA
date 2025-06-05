package com.seph_worker.worker.core.entity.CatNivelAcademico;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "cat_nivel_academico")
public class CatNivelAcademico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "cve", nullable = false)
    private String cve;

    @Basic
    @Column(name = "nivel_academico", nullable = false)
    private String nivelAcademico;
}
