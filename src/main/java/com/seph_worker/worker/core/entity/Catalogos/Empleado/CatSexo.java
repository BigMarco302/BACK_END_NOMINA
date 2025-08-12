package com.seph_worker.worker.core.entity.Catalogos.Empleado;

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
@Table(name = "cat_sexo")
@Where(clause = "deleted = false")
public class CatSexo extends AuditEntityN2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "cve_sexo", nullable = false)
    private Character cveSexo;

    @Basic
    @Column(name = "sexo", nullable = false)
    private String sexo;
}
