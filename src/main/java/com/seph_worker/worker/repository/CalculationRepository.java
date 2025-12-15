package com.seph_worker.worker.repository;


import com.seph_worker.worker.core.entity.IncidenciasInasistencias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CalculationRepository extends JpaRepository<IncidenciasInasistencias, Integer>{

    @Query(value = """
            
            SELECT EPC.id
            , tab_empleados_id, cat_plazas_id, EPC.cat_estatus_plaza_id, EPC.qnaini, EPC.qnafin, qna_proceso, EPC.cat_cct_id
            	,P.CVE_PLAZA
            	,P.CAT_CATEGORIAS_CVE
            	,C.CONCEPTO_CVE
            	,C.IMP_ZE2
            FROM public.nom_emp_pza_cct EPC
            INNER JOIN public.tab_plazas P ON EPC.cat_plazas_id = P.id
            INNER JOIN nom_categorias_cptos C ON P.CAT_CATEGORIAS_CVE = C.CAT_CATEGORIAS_CVE
            									AND C.QNAFIN = :qnaFin
            									AND C.NIVEL_SUELDO = :nivelSueldo
            									AND CONCEPTO_CVE IN (:concepto)
            
            """, nativeQuery = true)
    List<Map<String,Object>> getCalculation(Integer qnaFin, Integer nivelSueldo, List<String> concepto );
}
