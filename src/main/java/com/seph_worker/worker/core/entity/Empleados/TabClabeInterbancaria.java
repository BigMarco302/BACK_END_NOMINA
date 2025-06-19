package com.seph_worker.worker.core.entity.Empleados;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seph_worker.worker.core.entity.Cat.CatBanco;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "tab_clabes")
@Where(clause = "deleted = false")
public class TabClabeInterbancaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "clabe", length = 18)
    private String clabe;

    @Column(name = "estatus")
    private Boolean estatus;

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

    @ManyToOne
    @JoinColumn(name = "id_banco", referencedColumnName = "id", insertable = false, updatable = false)
    private CatBanco catBanco;
}
