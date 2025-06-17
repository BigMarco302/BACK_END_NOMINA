package com.seph_worker.worker.repository.Cat;


import com.seph_worker.worker.core.entity.Cat.CatEstadoCivil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatEstadoCivilRepository extends JpaRepository<CatEstadoCivil, Integer> {
}
