package com.seph_worker.worker.repository.Catalogos;


import com.seph_worker.worker.core.entity.Catalogos.CatCct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatCctRepository extends JpaRepository<CatCct, Integer> {
    Optional<CatCct> findByCveCt(String cveCt);
}
