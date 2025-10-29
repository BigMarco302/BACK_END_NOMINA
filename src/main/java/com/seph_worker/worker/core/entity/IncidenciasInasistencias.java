package com.seph_worker.worker.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seph_worker.worker.core.dto.AuditEntityN1;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@Entity
@Table(name = "incidencias_inasistencias")
public class IncidenciasInasistencias extends AuditEntityN1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "folio")
    private Long folio;

    @Column(name = "ejercicio")
    private Integer ejercicio;

    @Column(name = "qna_proceso")
    private Integer qnaProceso;

    @Column(name = "fecha_inasistencia")
    private LocalDate fechaInasistencia;

    @Column(name = "tipo_inasistencia", length = 1)
    private String tipoInasistencia;

    @Column(name = "horas_inasistencia")
    private Integer horasInasistencia;

    @Column(name = "tab_empleados_id")
    private Long tabEmpleadosId;

    @Column(name = "tab_plazas_id")
    private Long tabPlazasId;

    @Column(name = "us_created")
    private Integer usCreated;

    @Column(name = "ts_created")
    private Timestamp tsCreated;

    @Column(name = "us_modified")
    private Integer usModified;

    @Column(name = "ts_modified")
    private Timestamp tsModified;

    @Column(name = "us_deleted")
    private Integer usDeleted;

    @Column(name = "ts_deleted")
    private Timestamp tsDeleted;

    @Column(name = "deleted")
    private Boolean deleted;

}
