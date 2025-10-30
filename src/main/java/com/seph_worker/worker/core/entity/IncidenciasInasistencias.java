package com.seph_worker.worker.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seph_worker.worker.core.dto.AuditEntityN1;
import com.seph_worker.worker.core.entity.Tab.Empleados.TabEmpleado;
import com.seph_worker.worker.core.entity.Tab.TabPlazas;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@Entity
@Table(name = "incidencias_inasistencias")
public class IncidenciasInasistencias extends AuditEntityN1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "folio", nullable = false, length = 100)
    private Long folio;

    @Column(name = "ejercicio", nullable = false)
    private Integer ejercicio;

    @Column(name = "qna_proceso", nullable = false)
    private Integer qnaProceso;

    @Column(name = "fecha_inasistencia", nullable = false)
    private LocalDate fechaInasistencia;

    @Column(name = "tipo_inasistencia", length = 1)
    private String tipoInasistencia;

    @Column(name = "horas_inasistencia", nullable = false)
    private Integer horasInasistencia;

    @Column(name = "tab_empleados_id", nullable = false)
    private Long tabEmpleadoId;

    @Column(name = "tab_plazas_id", nullable = false)
    private Long tabPlazasId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tab_empleados_id", referencedColumnName = "id", insertable = false, updatable = false)
    private TabEmpleado tabEmpleado;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tab_plazas_id", referencedColumnName = "id", insertable = false, updatable = false)
    private TabPlazas tabPlazas;
}
