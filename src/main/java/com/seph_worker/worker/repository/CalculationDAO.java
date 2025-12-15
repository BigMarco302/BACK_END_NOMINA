package com.seph_worker.worker.repository;


import com.seph_worker.worker.core.dto.QueryUtils;
import com.seph_worker.worker.core.exception.ResourceNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class CalculationDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private String sqlLog(String whereCondition) {
        String sql = String.format("""
                      SELECT
                        EPC.tab_empleados_id,
                
                        json_agg(
                            json_build_object(
                                'id', EPC.id,
                                'catPlazasId', EPC.cat_plazas_id,
                                'catEstatusPlazaId', EPC.cat_estatus_plaza_id,
                                'qnaIni', EPC.qnaini,
                                'qnaFin', EPC.qnafin,
                                'qnaProceso', EPC.qna_proceso,
                                'catCctId', EPC.cat_cct_id,
                                'cvePlaza', P.CVE_PLAZA,
                                'catCategoriasCve', P.CAT_CATEGORIAS_CVE,
                                'conceptoCve', C.CONCEPTO_CVE,
                                'impZe2', C.IMP_ZE2
                            )
                        ) AS conceptos,
                
                        /* Sumatoria total */
                        SUM(C.IMP_ZE2) AS total_imp_ze2
                
                    FROM public.nom_emp_pza_cct EPC
                    INNER JOIN public.tab_plazas P 
                        ON EPC.cat_plazas_id = P.id
                    INNER JOIN nom_categorias_cptos C 
                        ON P.CAT_CATEGORIAS_CVE = C.CAT_CATEGORIAS_CVE
                
                    WHERE C.QNAFIN >= :qnaProceso
                    AND C.qnaini <= :qnaProceso
                    
                    %s

                    GROUP BY EPC.tab_empleados_id;
                """, whereCondition);

        return sql;
    }


    public Object getCalculation(Integer qnaProceso,Integer nivelSueldo, List<String> conceptos, Integer empleadoId){
        StringBuilder where = new StringBuilder();

        if(nivelSueldo != null)where.append(" AND C.NIVEL_SUELDO = :nivelSueldo ");
        if(conceptos != null && !conceptos.isEmpty())where.append("  AND C.CONCEPTO_CVE IN (:conceptos) ");
        if(empleadoId != null)where.append(" AND EPC.tab_empleados_id = :pEmpleadoId ");

        Query query = entityManager.createNativeQuery(sqlLog(where.toString()));
        query.setParameter("qnaProceso", qnaProceso);
        if(nivelSueldo != null)query.setParameter("nivelSueldo", nivelSueldo);
        if(conceptos != null && !conceptos.isEmpty())query.setParameter("conceptos", conceptos);
        if(empleadoId != null)query.setParameter("pEmpleadoId", empleadoId);

        QueryUtils.setResultTransformer(query, List.of("conceptos"),List.of());
        List<Map<String,Object>> result = query.getResultList();

        if(result == null || result.isEmpty())throw new ResourceNotFoundException("No se encotro informacion");

        return result;
    }

}
