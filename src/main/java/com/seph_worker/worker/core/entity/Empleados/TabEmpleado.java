package com.seph_worker.worker.core.entity.Empleados;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seph_worker.worker.core.dto.ListToJsonConverter;
import com.seph_worker.worker.core.entity.CatEstadoCivil.CatEstadoCivil;
import com.seph_worker.worker.core.entity.CatNivelAcademico.CatNivelAcademico;
import com.seph_worker.worker.core.entity.CatRegimen.CatRegimen;
import com.seph_worker.worker.core.entity.CatSexo.CatSexo;
import com.seph_worker.worker.core.entity.CatTipoContratacion.CatTipoContratacion;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name="tab_empleado")
@Where(clause = "deleted = false")
public class TabEmpleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "rfc", length = 13)
    private String rfc;

    @Column(name = "curp", length = 18)
    private String curp;

    @Column(name = "primer_apellido")
    private String primerApellido;

    @Column(name = "segundo_apellido")
    private String segundoApellido;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "qnaini")
    private Integer qnaini;

    @Column(name = "qnagob")
    private Integer qnagob;

    @Column(name = "qnasep")
    private Integer qnasep;

    @Column(name = "perfil")
    private String perfil;

    @Column(name = "nss")
    private String nss;

    @Basic
    @Column(name = "nivel", columnDefinition = "json")
    @Convert(converter = ListToJsonConverter.class)
    private List<Integer> nivel;

    //----------------------------------------->
    @Column(name = "cat_sexo_id")
    private Integer sexoId;

    @Column(name = "cat_estado_civil_id")
    private Integer estadoCivilId;

    @Column(name = "cat_regimen_id")
    private Integer regimenId;

    @Column(name = "cat_tipo_contratacion_id")
    private Integer tipoContratacionId;

    @Column(name = "nivel_academico_id")
    private Integer nivelAcademicoId;
    //----------------------------------------->

    @Column(name = "activo")
    private Boolean activo;

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

    // ------
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_sexo_id",  referencedColumnName = "id", insertable = false, updatable = false)
    private CatSexo catSexo;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_estado_civil_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CatEstadoCivil catEstadoCivil;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_regimen_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CatRegimen catRegimen;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_tipo_contratacion_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CatTipoContratacion catTipoContratacion;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nivel_academico_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CatNivelAcademico catNivelAcademico;

}
