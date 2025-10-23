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
    SELECT u.id,
           u.nombre,
           u.primer_apellido,
           u.segundo_apellido,
           u.rfc
    FROM tab_empleados u
    WHERE ( :target = 'RFC'              AND CAST(u.rfc AS TEXT) ILIKE :targetValue )
       OR ( :target = 'CURP'             AND CAST(u.curp AS TEXT) ILIKE :targetValue )
       OR ( :target = 'PRIMER_APELLIDO'  AND CAST(u.primer_apellido AS TEXT) ILIKE :targetValue )
       OR ( :target = 'SEGUNDO_APELLIDO' AND CAST(u.segundo_apellido AS TEXT) ILIKE :targetValue )
       OR (
            :target = 'NOMBRE'
        AND CAST(u.nombre AS TEXT) ILIKE COALESCE(:targetValue, CAST(u.nombre AS TEXT))
        AND CAST(u.primer_apellido AS TEXT) ILIKE COALESCE(:targetValuePrimer, CAST(u.primer_apellido AS TEXT))
        AND CAST(u.segundo_apellido AS TEXT) ILIKE COALESCE(:targetValueSegundo, CAST(u.segundo_apellido AS TEXT))
       )
""", nativeQuery = true)
List<Map<String, Object>> findEmployeesByTargetSearch(
        @Param("target") String target,
        @Param("targetValue") String targetValue,
        @Param("targetValuePrimer") String targetValuePrimer,
        @Param("targetValueSegundo") String targetValueSegundo);
}
