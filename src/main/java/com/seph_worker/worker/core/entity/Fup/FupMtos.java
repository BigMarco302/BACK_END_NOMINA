package com.seph_worker.worker.core.entity.Fup;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seph_worker.worker.core.dto.AuditEntityN1;
import com.seph_worker.worker.core.entity.Empleados.TabEmpleado;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Setter
@Getter
@Entity
@Table(name = "fup_mtos")
@Where(clause = "deleted = false")
public class FupMtos extends AuditEntityN1 {

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


    @Column(name = "propone_id", nullable = false)
    private Integer proponeId;

    @Column(name = "otorga_id", nullable = false)
    private Integer otorgaId;

    @Column(name = "autoriza_id", nullable = false)
    private Integer autorizaId;

}
