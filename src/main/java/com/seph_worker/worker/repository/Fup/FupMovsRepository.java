package com.seph_worker.worker.repository.Fup;


import com.seph_worker.worker.core.entity.Fup.FupMovs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FupMovsRepository extends JpaRepository<FupMovs, Integer> {
}
