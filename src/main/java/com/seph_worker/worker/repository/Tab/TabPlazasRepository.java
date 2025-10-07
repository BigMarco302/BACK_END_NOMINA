package com.seph_worker.worker.repository.Tab;


import com.seph_worker.worker.core.entity.Tab.Empleados.TabEmpleado;
import com.seph_worker.worker.core.entity.Tab.TabPlazas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface TabPlazasRepository extends JpaRepository<TabPlazas,Integer> {

        @Query(value = """
    SELECT *
    FROM tab_plazas p
    WHERE
        ( :target = 'PLAZA'    AND CAST(p.cve_plaza AS TEXT) ILIKE :targetValue )
     OR ( :target = 'CATEGORIA'   AND CAST(p.categoria AS TEXT) ILIKE :targetValue )
     OR ( :target = 'CCT_ID' AND CAST(p.cat_cct_id AS TEXT) ILIKE :targetValue );
            """, nativeQuery = true)
        List<TabPlazas> findPlazaByTarget(String target, String targetValue);

    @Query(value = """
        SELECT *
        FROM tab_plazas p
        WHERE REPLACE(p.cve_plaza, ' ', '') = :plaza
        """, nativeQuery = true)
    Optional<TabPlazas> findPlazaByPlaza(String plaza);
}
