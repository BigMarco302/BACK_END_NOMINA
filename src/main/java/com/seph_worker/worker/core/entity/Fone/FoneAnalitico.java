package com.seph_worker.worker.core.entity.Fone;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Setter
@Getter
@Entity
@Table(name = "fone_analitico")
public class FoneAnalitico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "ramo")
    private String ramo;

    @Column(name = "cve_ur")
    private String cveUr;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "modelo")
    private Integer modelo;

    @Column(name = "nivel")
    private String nivel;

    @Column(name = "cve_plaza")
    private String cvePlaza;

    @Column(name = "cve_ct", length = 10)
    private String cveCt;

    @Column(name = "zona_economica")
    private Integer zonaEconomica;

    @Column(name = "contratacion", length = 1)
    private String contratacion;

    @Column(name = "tipo_plaza", length = 1)
    private String tipoPlaza;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "tipo_transaccion", length = 1)
    private String tipoTransaccion;

    @Column(name = "estatus", length = 1)
    private String estatus;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "efecto_desde")
    private String efectoDesde;
}
