package com.seph_worker.worker.repository.Catalogos;


import com.seph_worker.worker.core.entity.Catalogos.CatSexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatSexoRepository extends JpaRepository<CatSexo, Integer> {
}
