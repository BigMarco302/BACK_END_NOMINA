package com.seph_worker.worker.core.entity.Tab.Empleados;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seph_worker.worker.core.dto.AuditEntityN1;
import com.seph_worker.worker.core.entity.Catalogos.Empleado.CatEstadoCivil;
import com.seph_worker.worker.core.entity.Catalogos.Empleado.CatNivelAcademico;
import com.seph_worker.worker.core.entity.Catalogos.Empleado.CatRegimen;
import com.seph_worker.worker.core.entity.Catalogos.Empleado.CatSexo;
import com.seph_worker.worker.core.entity.Catalogos.Empleado.CatTipoContratacion;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import java.util.List;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Setter
@Getter
@Entity
@Table(name="tab_empleados")
@Where(clause = "deleted = false")
public class TabEmpleado extends AuditEntityN1 {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "empleado_seq")
    @SequenceGenerator(name = "empleado_seq", sequenceName = "cat_empleados_id_empleado_seq", allocationSize = 1)
    private Long id;

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

    @Column(name = "activo")
    private Boolean activo;

    @Type(JsonType.class)
    @Column(name = "nivel", columnDefinition = "jsonb")
    private List<Integer> nivel;

    @Column(name = "cat_sexo_id")
    private Integer catSexoId;

    @Column(name = "cat_estado_civil_id")
    private Integer catEstadoCivilId;

    @Column(name = "cat_regimen_id")
    private Integer catRegimenId;

    @Column(name = "cat_tipo_contratacion_id")
    private Integer catTipoContratacionId;

    @Column(name = "nivel_academico_id")
    private Integer nivelAcademicoId;


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

    @JsonIgnore
    @OneToMany(mappedBy = "tabEmpleado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TabDocumentosEmpleado> documentosEmpleados;

    @JsonIgnore
    @OneToMany(mappedBy = "tabEmpleado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TabDomicilios> tabDomicilios;
}
