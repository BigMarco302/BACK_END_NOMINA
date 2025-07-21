package com.seph_worker.worker.core.entity.Fup;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seph_worker.worker.core.dto.AuditEntityN1;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Setter
@Getter
@Entity
@Table(name = "fup_conceptos")
@Where(clause = "deleted = false")
public class FupConceptos extends AuditEntityN1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "acuerdo",nullable = false)
    private Integer acuerdo;

    @Column(name = "cat_concepto_cve", nullable = false)
    private String catConceptoCve;

    @Column(name = "cat_plaza_id", nullable = false)
    private Integer catPlazaId;

    @Column(name = "qna_ini", nullable = false)
    private Integer qnaIni;

    @Column(name = "qna_fin", nullable = false)
    private Integer qnaFin;

    @Column(name = "importe",nullable = false)
    private BigDecimal importe;
}
