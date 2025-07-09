package com.seph_worker.worker.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.seph_worker.worker.core.entity.RoleModuleUser.CoreUser;
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
public class AuditEntityN2 {

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
