package com.seph_worker.worker.repository.EmpleadoRepository;

import com.seph_worker.worker.core.entity.Empleados.TabEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TabEmpleadoRepository extends JpaRepository<TabEmpleado, Integer> {
    List<TabEmpleado> findByTipoContratacionId(Integer tipoeContratacionId);
}
