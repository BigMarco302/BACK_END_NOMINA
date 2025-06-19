package com.seph_worker.worker.repository.Cat;

import com.seph_worker.worker.core.entity.Cat.CatBanco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatBancoRepository extends JpaRepository<CatBanco, Integer> {
}
