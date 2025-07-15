package com.seph_worker.worker.core.entity.Catalogos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seph_worker.worker.core.dto.AuditEntityN2;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Setter
@Getter
@Entity
@Table(name = "cat_nivel_academico")
@Where(clause = "deleted = false")
public class CatNivelAcademico extends AuditEntityN2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "cve", nullable = false)
    private String cve;

    @Basic
    @Column(name = "nivel_academico", nullable = false)
    private String nivelAcademico;
}
