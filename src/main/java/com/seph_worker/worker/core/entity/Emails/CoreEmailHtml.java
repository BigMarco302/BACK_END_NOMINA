package com.seph_worker.worker.core.entity.Emails;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="core_emails_html")
public class CoreEmailHtml {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "html", nullable = false)
    private String html;

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

}
