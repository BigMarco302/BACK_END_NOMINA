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
                
                          /* Nombre completo del empleado */
                          TRIM(
                              CONCAT(
                                  E.primer_apellido, ' ',
                                  E.segundo_apellido, ' ',
                                  E.nombre
                              )
                          ) AS Nombre_Empleado,
                
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
                                  'horas', P.HORAS,
                                  'conceptoCve', C.CONCEPTO_CVE,
                                  'importeQnal',
                                      CASE
                                          WHEN P.HORAS = 0 THEN (C.IMP_ZE2 / 2)
                                          ELSE C.IMP_ZE2 * P.HORAS
                                      END
                              )
                          ) AS conceptos,
                
                          /* Total por empleado */
                          SUM(
                              CASE
                                  WHEN P.HORAS = 0 THEN (C.IMP_ZE2 / 2)
                                  ELSE C.IMP_ZE2 * P.HORAS
                              END
                          ) AS total_importe_qnal
                
                      FROM public.nom_emp_pza_cct EPC
                
                      INNER JOIN public.tab_plazas P
                          ON EPC.cat_plazas_id = P.id
                
                      INNER JOIN nom_categorias_cptos C
                          ON LTRIM(P.CAT_CATEGORIAS_CVE,' ') = LTRIM(C.CAT_CATEGORIAS_CVE,' ')
                          AND C.NIVEL_SUELDO = EPC.NIVEL_SUELDO
                
                      INNER JOIN public.tab_empleados E
                          ON E.id = EPC.tab_empleados_id
                
                      WHERE EPC.cat_estatus_plaza_id IN ('1','6')
                        AND C.QNAFIN >= :qnaProceso
                        AND C.qnaini <= :qnaProceso
                
                        %s
                
                      GROUP BY
                          EPC.tab_empleados_id,
                          E.primer_apellido,
                          E.segundo_apellido,
                          E.nombre;
                
                """, whereCondition);

        return sql;
    }


    public Object getCalculation(Integer qnaProceso,Integer nivelSueldo, List<String> conceptos, Integer empleadoId, String tipoConcepto){
        StringBuilder where = new StringBuilder();

        if(nivelSueldo != null)where.append(" AND C.NIVEL_SUELDO = :nivelSueldo ");
        if(conceptos != null && !conceptos.isEmpty())where.append("  AND C.CONCEPTO_CVE IN (:conceptos) ");
        if(empleadoId != null)where.append(" AND EPC.tab_empleados_id = :pEmpleadoId ");
        if(tipoConcepto != null && tipoConcepto.equals("T"))where.append(" AND C.TIPO_CONCEPTO = :tipoConcepto ");

        Query query = entityManager.createNativeQuery(sqlLog(where.toString()));
        query.setParameter("qnaProceso", qnaProceso);
        if(nivelSueldo != null)query.setParameter("nivelSueldo", nivelSueldo);
        if(conceptos != null && !conceptos.isEmpty())query.setParameter("conceptos", conceptos);
        if(empleadoId != null)query.setParameter("pEmpleadoId", empleadoId);
        if (tipoConcepto != null && tipoConcepto.equals("T"))query.setParameter("tipoConcepto", tipoConcepto);


        QueryUtils.setResultTransformer(query, List.of("conceptos"),List.of());
        List<Map<String,Object>> result = query.getResultList();

        if(result == null || result.isEmpty())throw new ResourceNotFoundException("No se encotro informacion");

        return result;
    }

}
