package com.seph_worker.worker.core.entity.Cat;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seph_worker.worker.core.dto.AuditEntityN2;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@Entity
@Table(name = "cat_tipo_domicilio")
@Where(clause = "deleted = false")
public class CatTipoDomicilio extends AuditEntityN2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "tipo_domicilio", nullable = false)
    private String tipo_domicilio;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;
}
