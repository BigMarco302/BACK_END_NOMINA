package com.seph_worker.worker.repository.Tab.EmpleadoRepository;

import com.seph_worker.worker.core.entity.Tab.Empleados.TabEmpleado;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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


    @Query(value = """
            
            SELECT *
            FROM tab_empleados u
            WHERE
                ( :target = 'RFC'    AND CAST(u.rfc AS TEXT) ILIKE :targetValue )
             OR ( :target = 'CURP'   AND CAST(u.curp AS TEXT) ILIKE :targetValue )
             OR ( :target = 'NOMBRE' AND CAST(u.nombre AS TEXT) ILIKE :targetValue );
            """, nativeQuery = true)
    List<TabEmpleado> findEmployeesByTarget(String target, String targetValue);

@Query(value = """
    SELECT v.id,
           v.empleado
    FROM v_empleado v
    WHERE v.empleado ILIKE :pSearch
""", nativeQuery = true)
List<Map<String, Object>> findEmployeesByTargetSearch(String pSearch);
}
