package com.seph_worker.worker.core.entity.Empleados;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tab_domicilios")
public class TabDomicilios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "tab_empleado_id", nullable = false)
    private Long tabEmpleadoId;

    @Basic
    @Column(name = "calle", nullable = false)
    private String calle;

    @Basic
    @Column(name = "num_int", nullable = false)
    private String num_int;
    
    @Basic
    @Column(name = "num_ext", nullable = false)
    private String num_ext;

    @Basic
    @Column(name = "colonia", nullable = false)
    private String colonia;
    
    @Basic
    @Column(name = "cve_ent", nullable = false)
    private String cve_ent;

    @Basic
    @Column(name = "cve_mun", nullable = false)
    private String cve_mun;
    
    @Basic
    @Column(name = "cve_loc", nullable = false)
    private String cve_loc;

    @Basic
    @Column(name = "cp", nullable = false)
    private String cp;
    
    @Basic
    @Column(name = "cat_tipo_domicilio_id", nullable = false)
    private String cat_tipo_domicilio_id;

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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tab_empleado_id", referencedColumnName = "id", insertable = false, updatable = false)
    private TabEmpleado tabEmpleado;

}
