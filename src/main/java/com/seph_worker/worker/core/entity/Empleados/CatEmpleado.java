package com.seph_worker.worker.core.entity.Empleados;


import com.seph_worker.worker.core.dto.ListToJsonConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name="cat_empleados")
@Where(clause = "deleted = false")
public class CatEmpleado {

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

    @Column(name = "cat_sexo_id")
    private Integer sexoId;

    @Column(name = "cat_estado_civil_id")
    private Integer estadoCivilId;

    @Column(name = "qnaini")
    private Integer qnaini;

    @Column(name = "qnagob")
    private Integer qnagob;

    @Column(name = "qnasep")
    private Integer qnasep;

    @Column(name = "cat_nivel_academico_id")
    private Integer nivelAcademicoId;

    @Column(name = "perfil")
    private String perfil;

    @Column(name = "nss")
    private String nss;

    @Column(name = "cat_regimen_id")
    private Integer regimenId;

    @Column(name = "cat_tipo_contratacion")
    private Integer tipoContratacionId;

    @Basic
    @Column(name = "nivel", columnDefinition = "json")
    @Convert(converter = ListToJsonConverter.class)
    private List<Integer> nivel;

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
}
