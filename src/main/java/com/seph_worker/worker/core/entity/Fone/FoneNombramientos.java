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
public class FoneNombramientos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "entidad_federativa")
    private String entidadFederativa;

    @Column(name = "curp", length = 18)
    private String curp;

    @Column(name = "rfc", length = 13)
    private String rfc;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "cp", length = 5)
    private String cp;

    @Column(name = "cve_plaza")
    private String cvePlaza;

    @Column(name = "estatus_plaza", length = 1)
    private String estatusPlaza;

    @Column(name = "estatus_nombramiento")
    private String estatusNombramiento;

    @Column(name = "movimiento")
    private String movimiento;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "vigente")
    private String vigente;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_inicial")
    private String fechaInicial;

    @Column(name = "fecha_final")
    private String fechaFinal;

    @Column(name = "fecha_baja")
    private String fechaBaja;

    @Column(name = "regimen")
    private String regimen;

    @Column(name = "ticked")
    private String ticked;

    @Column(name = "excepcion")
    private String excepcion;

    @Column(name = "extemporaneo")
    private String extemporaneo;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;
}
