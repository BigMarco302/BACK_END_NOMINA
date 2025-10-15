package com.seph_worker.worker.core.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seph_worker.worker.core.dto.AuditEntityN2;
import com.seph_worker.worker.core.dto.AuditEntityPrincipal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tab_plazas")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Where(clause = "deleted = false")
public class NomBeneficiariosAlim extends AuditEntityPrincipal {

    @Column(name = "tab_empleados_id")
    private Integer tabEmpleadosId;

    @Column(name = "tab_beneficiarios_alim_id")
    private Integer tabBeneficiariosAlimId;

    @Column(name = "cat_cct_id")
    private Integer catCctId;

    @Column(name = "forma_aplicacion")
    private Character formaAplicacion;

    @Column(name = "factor_importe")
    private BigDecimal factorImporte;

    @Column(name = "numero_benef")
    private Integer numeroBenef;

    @Column(name = "qnaini")
    private Integer qnaini;

    @Column(name = "qnafin")
    private Integer qnafin;

    @Column(name = "numero_documento")
    private String numeroDocumento;

    @Column(name = "fecha_documento")
    private LocalDate fechaDocumento;
}
