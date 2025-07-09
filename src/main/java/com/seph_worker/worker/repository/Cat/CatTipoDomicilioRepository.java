package com.seph_worker.worker.repository.Cat;


import com.seph_worker.worker.core.entity.Cat.CatTipoDomicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatTipoDomicilioRepository extends JpaRepository<CatTipoDomicilio, Integer> {
}
