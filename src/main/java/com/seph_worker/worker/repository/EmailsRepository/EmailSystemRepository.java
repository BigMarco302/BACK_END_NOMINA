package com.seph_worker.worker.repository.EmailsRepository;

import com.seph_worker.worker.core.entity.Emails.CoreEmailSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailSystemRepository extends JpaRepository<CoreEmailSystem, Integer> {
}
