package com.seph_worker.worker.repository.Tab.EmpleadoRepository;


import com.seph_worker.worker.core.entity.Tab.Empleados.TabClabes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TabClabesRepository extends JpaRepository<TabClabes, Integer> {

    @Query(value = """
        SELECT c.id,
               c.clabe,
               c.target_id,
               c.estatus,
               c.cat_clabe_id,
               cb.cve_banco,
               cb.banco,
               cb.id AS banco_id
        FROM tab_clabes c
        INNER JOIN cat_clabes cc ON c.cat_clabe_id = cc.id AND cc.deleted = false
        INNER JOIN cat_bancos cb ON c.cat_banco_id = cb.id AND cb.deleted = false
        WHERE c.deleted = false
        AND c.cat_clabe_id = :catClabeId
""",nativeQuery = true)
    List<Map<String,Object>> getClabes (Integer catClabeId);

        @Query(value = """
        SELECT c.id,
               c.clabe,
               c.target_id,
               c.estatus,
               c.cat_clabe_id,
               cb.cve_banco,
               cb.banco,
               cb.id AS banco_id
        FROM tab_clabes c
        INNER JOIN cat_clabes cc ON c.cat_clabe_id = cc.id AND cc.deleted = false
        INNER JOIN cat_bancos cb ON c.cat_banco_id = cb.id AND cb.deleted = false
        WHERE c.deleted = false
        AND c.cat_clabe_id = :catClabeId
        AND c.id = :clabeId
""",nativeQuery = true)
    List<Map<String,Object>> getClabesById (Integer catClabeId, Integer clabeId);

                @Query(value = """
        SELECT c.id,
               c.clabe,
               c.target_id,
               c.estatus,
               c.cat_clabe_id,
               cb.cve_banco,
               cb.banco,
               cb.id AS banco_id
        FROM tab_clabes c
        INNER JOIN cat_clabes cc ON c.cat_clabe_id = cc.id AND cc.deleted = false
        INNER JOIN cat_bancos cb ON c.cat_banco_id = cb.id AND cb.deleted = false
        WHERE c.deleted = false
        AND c.cat_clabe_id = :catClabeId
        AND c.target_id = :targetId
""",nativeQuery = true)
    List<Map<String,Object>> getClabesByTarget (Integer catClabeId, Integer targetId);
}
