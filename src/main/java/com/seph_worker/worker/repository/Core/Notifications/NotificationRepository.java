package com.seph_worker.worker.repository.Core.Notifications;


import com.seph_worker.worker.core.entity.Core.Notifications.CoreNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<CoreNotification, Integer> {

    @Query(value = """
SELECT
    tn.id
FROM core_type_notification tn
WHERE tn.name=:name
""", nativeQuery = true)
    Integer getIdByName(String name);

}
