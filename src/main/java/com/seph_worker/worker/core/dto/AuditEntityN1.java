package com.seph_worker.worker.core.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seph_worker.worker.core.entity.Core.RoleModuleUser.CoreUser;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@MappedSuperclass
@Getter
@Setter
public class AuditEntityN1 {

    @Column(name = "us_created")
    private Integer usCreated;

    @ManyToOne
    @JoinColumn(name = "us_created", referencedColumnName = "id", insertable = false, updatable = false)
    private CoreUser coreUserCreated;

    @Column(name = "ts_created")
    private Timestamp tsCreated;

    @Column(name = "us_modified")
    private Integer usModified;

    @ManyToOne
    @JoinColumn(name = "us_modified", referencedColumnName = "id", insertable = false, updatable = false)
    private CoreUser coreUserModified;

    @Column(name = "ts_modified")
    private Timestamp tsModified;

    @Column(name = "us_deleted")
    private Integer usDeleted;

    @ManyToOne
    @JoinColumn(name = "us_deleted", referencedColumnName = "id", insertable = false, updatable = false)
    private CoreUser coreUserDeleted;

    @Column(name = "ts_deleted")
    private Timestamp tsDeleted;

    @Column(name = "deleted")
    private Boolean deleted = Boolean.FALSE;
}
