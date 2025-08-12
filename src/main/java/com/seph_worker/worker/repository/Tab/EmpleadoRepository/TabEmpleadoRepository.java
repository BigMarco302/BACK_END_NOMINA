package com.seph_worker.worker.repository.Tab.EmpleadoRepository;

import com.seph_worker.worker.core.entity.Tab.Empleados.TabEmpleado;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TabEmpleadoRepository extends JpaRepository<TabEmpleado, Integer> {

    @Query(value = """
    SELECT
        te.*
    FROM tab_empleados te
    WHERE te.deleted = false
    AND te.cat_tipo_contratacion_id != 10
    ORDER BY te.id DESC
""", nativeQuery = true)
    List<TabEmpleado> findEmpleaadosNotBase(Pageable pageable);

    @Query(value = """
    SELECT
        te.*
    FROM tab_empleados te
    WHERE te.deleted = false
    AND te.cat_tipo_contratacion_id =:catTipoContratacionId
    ORDER BY te.id DESC
""", nativeQuery = true)
    List<TabEmpleado> findEmpleaados(Pageable pageable, Integer catTipoContratacionId);
}
