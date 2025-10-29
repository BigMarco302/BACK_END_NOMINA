package com.seph_worker.worker.repository.DAOS;

import com.seph_worker.worker.core.dto.QueryUtils;
import com.seph_worker.worker.core.exception.ResourceNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class TabEmpleadoDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private String sqlLog(String moduleName, String whereCondition) {
        String sql = String.format("""
                    SELECT *
                    FROM %s l
                     %s
                """, moduleName, whereCondition);
        return sql;
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getEmployeesByTargetSearch(String search) {
        try {
            if (search == null || search.trim().isEmpty()) {
                throw new ResourceNotFoundException("Debe proporcionar un valor de búsqueda válido.");
            }

            // Divide las palabras (MARCO VAZQUEZ AGUILAR → ["MARCO","VAZQUEZ","AGUILAR"])
            String[] words = search.trim().split("\\s+");

            // Crea las condiciones dinámicas
            String whereClause = Arrays.stream(words)
                    .map(word -> "l.empleado ILIKE '%" + word + "%'")
                    .collect(Collectors.joining(" AND "));

            // ✅ Arma el SQL final usando el método sqlLog (solo pasa el nombre base, sin alias adicional)
            String sql = sqlLog("v_empleado", "WHERE " + whereClause);

            // Ejecuta la consulta
            Query query = entityManager.createNativeQuery(sql);

            // Transforma los resultados a Map (como siempre haces)
            QueryUtils.setResultTransformerWithExcludeColumns(
                    query,
                    List.of("ts_deleted", "us_deleted", "deleted")
            );

            List<Map<String, Object>> result = query.getResultList();

            return result.isEmpty() ? Collections.emptyList() : result;

        } catch (Exception e) {
            throw new ResourceNotFoundException("Error al ejecutar la búsqueda dinámica: " + e.getMessage());
        }
    }
}
