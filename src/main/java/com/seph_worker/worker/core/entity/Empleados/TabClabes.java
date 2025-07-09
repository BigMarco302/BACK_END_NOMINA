package com.seph_worker.worker.core.entity.Empleados;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seph_worker.worker.core.dto.AuditEntityN1;
import com.seph_worker.worker.core.entity.Cat.CatBanco;
import com.seph_worker.worker.core.entity.Cat.CatClabe;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@Entity
@Table(name = "tab_clabes")
@Where(clause = "deleted = false")
public class TabClabes extends AuditEntityN1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "target_id", nullable = false)
    private Integer targetId;

    @Column(name = "clabe", length = 18, nullable = false)
    private String clabe;

    @Column(name = "estatus")
    private Boolean estatus;

    @Column(name = "cat_banco_id", nullable = false)
    private Integer catBancoId;

    @Column(name = "cat_clabe_id", nullable = false)
    private Integer cat_clabe_id;

    @ManyToOne
    @JoinColumn(name = "cat_banco_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CatBanco catBanco;

    @ManyToOne
    @JoinColumn(name = "cat_clabe_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CatClabe catClabe;
}
