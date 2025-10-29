package com.seph_worker.worker.model;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class IncidenciasInasistenciasDTO {

    private Long id;
    private Long folio;
    private Integer ejercicio;
    private Integer qnaProceso;
    private LocalDate fechaInasistencia;
    private String tipoInasistencia;
    private Integer horasInasistencia;
    private Long tabEmpleadosId;
    private Long tabPlazasId;

    private Integer usCreated;
    private Timestamp tsCreated;
    private Integer usModified;
    private Timestamp tsModified;
    private Integer usDeleted;
    private Timestamp tsDeleted;
    private Boolean deleted;
}
