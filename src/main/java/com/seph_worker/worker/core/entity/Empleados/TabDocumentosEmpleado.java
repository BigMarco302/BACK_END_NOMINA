package com.seph_worker.worker.core.entity.Empleados;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seph_worker.worker.core.entity.Cat.CatDocumento;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tab_documentos_empleado")
public class TabDocumentosEmpleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    // ----- ----- ----- ----- ----- ----- -----
    @Basic
    @Column(name = "cat_empleado_id", nullable = false)
    private Integer cat_empleado_id;

    @Basic
    @Column(name = "cat_documento_id", nullable = false)
    private Integer cat_documento_id;
    // ----- ----- ----- ----- ----- ----- -----

    @Column(name = "path", nullable = false)
    private Integer path;

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
    @JoinColumn(name = "cat_documento_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CatDocumento catDocumento;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tab_empleado", referencedColumnName = "id", insertable = false, updatable = false)
    private TabEmpleado tabEmpleado;
    
 }
