package com.seph_worker.worker.core.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seph_worker.worker.core.dto.AuditEntityN1;
import com.seph_worker.worker.core.dto.AuditEntityN2;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Entity
@Table(name = "tab_beneficiarios_alim")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Where(clause = "deleted = false")
public class TabBeneficiariosAlim extends AuditEntityN1 {

        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "rfc", nullable = false)
    private String rfc;


    @Column(name = "primer_apellido", nullable = false)
    private String primerApellido;

        @Column(name = "segundo_apellido")
    private String segundoApellido;

          @Column(name = "nombre", nullable = false)
    private String nombre;

}
