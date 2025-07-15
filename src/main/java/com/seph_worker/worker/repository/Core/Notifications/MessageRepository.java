package com.seph_worker.worker.repository.Core.Notifications;

import com.seph_worker.worker.core.entity.Core.Notifications.CoreMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<CoreMessage, Integer> {
}
