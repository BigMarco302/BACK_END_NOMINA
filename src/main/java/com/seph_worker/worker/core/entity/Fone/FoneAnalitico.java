package com.seph_worker.worker.core.entity.Fone;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seph_worker.worker.core.dto.ExcelColumn;
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

    @ExcelColumn(index = 0)
    @Column(name = "ramo")
    private String ramo;

    @ExcelColumn(index = 1)
    @Column(name = "cve_ur")
    private String cveUr;

    @ExcelColumn(index = 2)
    @Column(name = "categoria")
    private String categoria;

    @ExcelColumn(index = 3, type = "INTEGER")
    @Column(name = "modelo")
    private Integer modelo;

    @ExcelColumn(index = 4)
    @Column(name = "nivel")
    private String nivel;

    @ExcelColumn(index = 5)
    @Column(name = "cve_plaza")
    private String cvePlaza;

    @ExcelColumn(index = 6)
    @Column(name = "cve_ct", length = 10)
    private String cveCt;

    @ExcelColumn(index = 7, type = "INTEGER")
    @Column(name = "zona_economica")
    private Integer zonaEconomica;

    @ExcelColumn(index = 8)
    @Column(name = "contratacion", length = 1)
    private String contratacion;

    @ExcelColumn(index = 9)
    @Column(name = "tipo_plaza", length = 1)
    private String tipoPlaza;

    @ExcelColumn(index = 10, type = "INTEGER")
    @Column(name = "cantidad")
    private Integer cantidad;

    @ExcelColumn(index = 11)
    @Column(name = "tipo_transaccion", length = 1)
    private String tipoTransaccion;

    @ExcelColumn(index = 12)
    @Column(name = "estatus", length = 1)
    private String estatus;

    @ExcelColumn(index = 13, type = "DATE")
    @Column(name = "fecha")
    private LocalDateTime fecha;

    @ExcelColumn(index = 14)
    @Column(name = "efecto_desde")
    private String efectoDesde;
}
