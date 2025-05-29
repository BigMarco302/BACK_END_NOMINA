package com.seph_worker.worker.repository.Notifications;


import com.seph_worker.worker.core.entity.Notifications.CoreIcon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IconRepository extends JpaRepository<CoreIcon, Integer> {



    @Query(value = """
SELECT
    ino.id
FROM core_icon_notification ino
WHERE ino.name=:name
""", nativeQuery = true)
    Integer getIdByName(String name);
}

