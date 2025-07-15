package com.seph_worker.worker.repository.Core.EmailsRepository;

import com.seph_worker.worker.core.entity.Emails.CoreEmailHtml;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailHtmlRepository extends JpaRepository<CoreEmailHtml, Integer> {


    Optional<CoreEmailHtml> findByName(String typeHtml);
}
