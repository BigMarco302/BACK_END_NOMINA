package com.seph_worker.worker.repository.Notifications;

import com.seph_worker.worker.core.entity.Notifications.CoreMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<CoreMessage, Integer> {
}
