package com.seph_worker.worker.core.entity.Empleados;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seph_worker.worker.core.dto.AuditEntityN1;
import com.seph_worker.worker.core.entity.Catalogos.CatDocumento;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Setter
@Getter
@Entity
@Table(name = "tab_documentos_empleado")
@Where(clause = "deleted = false")
public class TabDocumentosEmpleado extends AuditEntityN1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "tab_empleado_id", nullable = false)
    private Integer tabEmpleadoId;

    @Basic
    @Column(name = "cat_documento_id", nullable = false)
    private Integer catDocumentoId;

    @Column(name = "path", nullable = false)
    private Integer path;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_documento_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CatDocumento catDocumento;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tab_empleado_id", referencedColumnName = "id", insertable = false, updatable = false)
    private TabEmpleado tabEmpleado;
    
 }
