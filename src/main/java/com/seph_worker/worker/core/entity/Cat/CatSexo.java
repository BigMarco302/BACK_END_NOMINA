package com.seph_worker.worker.core.entity.Cat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "cat_sexo")
public class CatSexo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "cve_sexo", nullable = false)
    private Character cveSexo;

    @Basic
    @Column(name = "sexo", nullable = false)
    private String sexo;
}
