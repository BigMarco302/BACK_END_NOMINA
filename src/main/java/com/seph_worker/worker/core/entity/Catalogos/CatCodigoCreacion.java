package com.seph_worker.worker.core.entity.Catalogos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@Entity
@Table(name = "cat_codigo_creacion")
public class CatCodigoCreacion {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_codigo_creacion", nullable = false)
    private Integer id;

    @Column(name = "codigo_creacion", nullable = false)
    private String codigoCreacion;

}
