package com.seph_worker.worker.core.entity.Fup;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seph_worker.worker.core.dto.AuditEntityN1;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Setter
@Getter
@Entity
@Table(name = "fup_movs")
@Where(clause = "deleted = false")
public class FupMovs extends AuditEntityN1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "acuerdo",nullable = false)
    private Integer acuerdo;

    @Column(name = "cat_plaza_id", nullable = false)
    private Integer catPlazaId;

    @Column(name = "cat_cct_id", nullable = false)
    private Integer catCctId;

    @Column(name = "cat_movimiento_cve", nullable = false)
    private String catMovimientoCve;

    @Column(name = "cat_motivo_cve", nullable = false)
    private String catMotivoCve;

    @Column(name = "qna_fin", nullable = false)
    private Integer qnaFin;

    @Column(name = "qna_ini", nullable = false)
    private Integer qnaIni;

    @Column(name = "antece")
    private Integer antece;
}
