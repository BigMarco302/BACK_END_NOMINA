package com.seph_worker.worker.repository.Catalogos;

import com.seph_worker.worker.core.entity.Cat.CatClabe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatClabeRepository extends JpaRepository <CatClabe, Integer>{
}
