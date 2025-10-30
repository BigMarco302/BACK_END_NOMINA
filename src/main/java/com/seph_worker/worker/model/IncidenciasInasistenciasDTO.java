package com.seph_worker.worker.model;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class IncidenciasInasistenciasDTO {

    private Integer id;
    private Long folio;
    private Integer ejercicio;
    private Integer qnaProceso;
    private LocalDate fechaInasistencia;
    private String tipoInasistencia;
    private Integer horasInasistencia;
    private Long tabEmpleadosId;
    private Long tabPlazasId;

}
