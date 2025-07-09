package com.seph_worker.worker.repository.EmpleadoRepository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TabClabesRepository extends JpaRepository<com.seph_worker.worker.core.entity.Empleados.TabClabes, Integer> {
}
