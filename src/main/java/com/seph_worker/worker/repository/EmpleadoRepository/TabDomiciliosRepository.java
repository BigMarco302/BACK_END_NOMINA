package com.seph_worker.worker.repository.EmpleadoRepository;


import com.seph_worker.worker.core.entity.Empleados.TabDomicilios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TabDomiciliosRepository extends JpaRepository<TabDomicilios, Integer> {

}
