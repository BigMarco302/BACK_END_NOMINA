package com.seph_worker.worker.repository;


import com.seph_worker.worker.core.entity.NomBeneficiariosAlim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BeneficiariosRepository extends JpaRepository<NomBeneficiariosAlim,Integer> {


    @Query(value = """
SELECT *
FROM nom_beneficiarios_alim nom
INNER JOIN tab_empleados empleado ON nom.tab_empleados_id = empleado.id AND empleado.deleted = false
INNER JOIN tab_beneficiarios_alim alim ON nom.tab_beneficiarios_alim_id = alim.id AND alim.deleted = false
LEFT JOIN cat_cct cct ON nom.cat_cct_id = cct.id
WHERE nom.tab_beneficiarios_alim_id = :pBeneficiarioId
""",nativeQuery = true)
    List<Map<String,Object>> getBeneficiarioId(Integer pBeneficiarioId);
}
