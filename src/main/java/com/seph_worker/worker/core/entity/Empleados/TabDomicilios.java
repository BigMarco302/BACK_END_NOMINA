package com.seph_worker.worker.core.entity.Empleados;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seph_worker.worker.core.dto.AuditEntityN1;

import com.seph_worker.worker.core.entity.Catalogos.Direcciones.CatTipoDomicilio;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@Entity
@Table(name = "tab_domicilios")
@Where(clause = "deleted = false")
public class TabDomicilios extends AuditEntityN1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "tab_empleado_id", nullable = false)
    private Integer tabEmpleadoId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tab_empleado_id", referencedColumnName = "id", insertable = false, updatable = false)
    private TabEmpleado tabEmpleado;

    @Column(name = "calle")
    private String calle;

    @Column(name = "num_int")
    private String numInt;

    @Column(name = "num_ext")
    private String numExt;

    @Column(name = "colonia")
    private String colonia;

    @Column(name = "cat_entidad_cve_ent", nullable = false)
    private String catEntidadCveEnt;

    @Column(name = "cat_municipio_cve_mun", nullable = false)
    private String catMunicipioCveMun;

    @Column(name = "cat_localidad_cve_loc", nullable = false)
    private String catLocalidadCveLoc;

    @Column(name = "cp", length = 5, nullable = false)
    private String cp;

    @Column(name = "cat_tipo_domicilio_id", nullable = false)
    private Integer catTipoDomicilioId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_tipo_domicilio_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CatTipoDomicilio catTipoDomicilio;
}
