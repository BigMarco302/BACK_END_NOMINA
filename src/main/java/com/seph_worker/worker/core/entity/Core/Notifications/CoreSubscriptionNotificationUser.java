package com.seph_worker.worker.core.entity.Core.Notifications;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Setter
@Getter
@Entity
@Table(name="core_subscriptions_notifications_users")
public class CoreSubscriptionNotificationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    @Basic
    @Column(name = "notification_id", nullable = false)
    private Integer notificationId;

    @Basic
    @Column(name = "user_id", nullable = false)
    private Integer userId;



}
