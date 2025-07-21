package com.seph_worker.worker.repository.Fup;

import com.seph_worker.worker.core.entity.Fup.FupConceptos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FupConceptosRepository extends JpaRepository<FupConceptos, Integer> {
}
