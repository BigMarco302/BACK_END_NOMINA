package com.seph_worker.worker.core.entity.Cat;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "cat_banco")
@Where(clause = "deleted = false")
public class CatBanco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "banco_clave", nullable = false)
    private Integer bancoClave;

    @Column(name = "banco")
    private String banco;

    @Column(name = "ts_deleted")
    private Timestamp tsDeleted;

    @Column(name = "deleted")
    private Boolean deleted;
}
