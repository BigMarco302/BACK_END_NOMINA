package com.seph_worker.worker.repository.fone;


import com.seph_worker.worker.core.entity.Fone.FoneAnalitico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoneAnaliticoRepository extends JpaRepository<FoneAnalitico, Integer> {
}
