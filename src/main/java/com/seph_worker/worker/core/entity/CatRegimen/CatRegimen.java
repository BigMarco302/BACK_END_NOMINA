package com.seph_worker.worker.core.entity.CatRegimen;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "cat_regimen")
public class CatRegimen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "regimen", nullable = false)
    private String regimen;

}
