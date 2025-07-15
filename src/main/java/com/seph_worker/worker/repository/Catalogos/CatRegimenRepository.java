package com.seph_worker.worker.repository.Catalogos;


import com.seph_worker.worker.core.entity.Cat.CatRegimen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRegimenRepository extends JpaRepository<CatRegimen, Integer> {
}
