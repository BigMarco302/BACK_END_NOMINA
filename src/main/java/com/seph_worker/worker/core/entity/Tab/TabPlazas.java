package com.seph_worker.worker.core.entity.Tab;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seph_worker.worker.core.dto.AuditEntityN1;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tab_plazas")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Where(clause = "deleted = false")
public class TabPlazas extends AuditEntityN1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "cve_plaza", length = 24, nullable = false)
    private String cvePlaza;

    @Column(name = "cod_pago", length = 2, nullable = false)
    private String codPago;

    @Column(name = "unidad", length = 2, nullable = false)
    private String unidad;

    @Column(name = "subunidad", length = 2, nullable = false)
    private String subunidad;

    @Column(name = "categoria", length = 7, nullable = false)
    private String categoria;

    @Column(name = "horas", precision = 2, scale = 1, nullable = false)
    private Double horas;

    @Column(name = "cons_plaza", nullable = false)
    private Integer consPlaza;

    @Column(name = "qna_ini", nullable = false)
    private Integer qnaIni;

    @Column(name = "qna_fin", nullable = false)
    private Integer qnaFin;

    @Column(name = "folio", nullable = false)
    private String folio;

    @Column(name = "fecha_folio", nullable = false)
    private LocalDateTime fechaFolio;

    @Column(name = "cat_cct_id", nullable = false)
    private Integer catCctId;

    @Column(name = "cat_codigo_creacion_id", nullable = false)
    private Integer catCodigoCreacionId;

    @Column(name = "cat_estatus_plaza_id", nullable = false)
    private Integer catEstatusPlazaId;
}
