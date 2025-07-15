package com.seph_worker.worker.repository.DAOS;


import com.seph_worker.worker.core.dto.QueryUtils;
import com.seph_worker.worker.core.exception.ResourceNotFoundException;
import com.seph_worker.worker.model.EnumClass.CatTypesEnum;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CatTypeDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private String sqlLog(String moduleName, String whereCondition) {
        String sql = String.format("""
                    SELECT *
                    FROM %s l
                    WHERE l.deleted = false
                        %s
                """, moduleName, whereCondition);

        return sql;
    }
    public Object getCatType(CatTypesEnum catType, String whereCondition, String typeConditionString, Integer typeConditionInteger){
        try {
            String whereConditionSql = "";
            boolean hasCondition = whereCondition != null && (typeConditionString != null || typeConditionInteger != null);

            if(hasCondition)whereConditionSql = " AND l."+whereCondition+" = :entityType";

            Query query = entityManager.createNativeQuery(sqlLog(catType.getValue(),whereConditionSql));

            QueryUtils.setResultTransformerWithExcludeColumns(query, List.of("ts_deleted","us_deleted","deleted"));

            if (hasCondition) {
                Object conditionValue = (typeConditionString != null) ? typeConditionString : typeConditionInteger;
                query.setParameter("entityType", conditionValue);
            }

            List<?> resultList = query.getResultList();

            if (resultList == null || resultList.isEmpty()) {
                return null;
            }
            if (resultList.size() == 1) {
                return resultList.get(0);
            }

            return resultList;
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error al ejecutar la consulta, favor de revisar parametros: "+e.getMessage());
        }

    }
}
