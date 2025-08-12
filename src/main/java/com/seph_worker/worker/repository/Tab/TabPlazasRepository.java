package com.seph_worker.worker.repository.Tab;


import com.seph_worker.worker.core.entity.Tab.TabPlazas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TabPlazasRepository extends JpaRepository<TabPlazas,Integer> {
}
