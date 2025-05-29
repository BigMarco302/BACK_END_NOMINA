package com.seph_worker.worker.core.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name="core_tokens_verify")
public class CoreTokensVerify {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "token", nullable = false)
    private String token;

    @Basic
    @Column(name = "user_id", nullable = false)
    private Integer userId;


    @Basic
    @Column(name = "ts_created", nullable = false)
    private Timestamp ts_created;


    @Basic
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;


}
