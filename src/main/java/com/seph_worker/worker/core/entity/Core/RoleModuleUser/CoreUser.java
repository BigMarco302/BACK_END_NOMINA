package com.seph_worker.worker.core.entity.Core.RoleModuleUser;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seph_worker.worker.core.dto.MapToJsonConverter;
import com.seph_worker.worker.core.entity.Tab.Empleados.TabEmpleado;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.util.Map;
@Setter
@Getter
@Entity
@Table(name="core_users")
@Where(clause = "deleted = false")

public class CoreUser {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


    @Column(name = "config")
    @Convert(converter = MapToJsonConverter.class)
    private Map<String, Object> config;

    @Basic
    @Column(name = "cat_empleado_id", nullable = false)
    private Integer catEmpleadoId;

    @Basic
    @Column(name = "username", nullable = false)
    private String username;

    @Basic
    @Column(name = "pass", nullable = false)
    private String pass;

    @Basic
    @Column(name = "area", nullable = false)
    private String area;

    @Basic
    @Column(name = "task", nullable = false)
    private String task;

    @Basic
    @Column(name = "active", nullable = false)
    private Boolean active;

    @Basic
    @Column(name = "email", nullable = false)
    private String email;

    @Basic
    @Column(name = "is_verified", nullable = false)
    private Boolean IsVerified;

    @Basic
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @Basic
    @Column(name = "is_password", nullable = false)
    private Boolean isPassword;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_empleado_id", referencedColumnName = "id", insertable = false, updatable = false)
    private TabEmpleado catEmpleado;

}
