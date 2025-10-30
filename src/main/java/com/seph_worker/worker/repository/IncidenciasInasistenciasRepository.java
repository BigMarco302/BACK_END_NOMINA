package com.seph_worker.worker.repository;


import com.seph_worker.worker.core.entity.IncidenciasInasistencias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface IncidenciasInasistenciasRepository extends JpaRepository<IncidenciasInasistencias, Integer> {

    @Query(value = """
        SELECT *
        FROM incidencias_inasistencias
        WHERE (deleted = FALSE OR deleted IS NULL)
          AND (:empleadoId IS NULL OR tab_empleados_id = :empleadoId)
          AND (:qnaProceso IS NULL OR qna_proceso = :qnaProceso)
          AND (:desde IS NULL OR fecha_inasistencia >= :desde)
          AND (:hasta IS NULL OR fecha_inasistencia <= :hasta)
          AND (:tipo IS NULL OR tipo_inasistencia = :tipo)
        ORDER BY fecha_inasistencia DESC, id DESC
        """, nativeQuery = true)
    List<Map<String, Object>> findAllFlat(
            @Param("empleadoId") Long empleadoId,
            @Param("qnaProceso") Integer qnaProceso,
            @Param("desde") LocalDate desde,
            @Param("hasta") LocalDate hasta,
            @Param("tipo") String tipo
    );

    @Query(value = """
        SELECT *
        FROM incidencias_inasistencias
        WHERE (deleted = FALSE OR deleted IS NULL)
          AND id = :id
        """, nativeQuery = true)
    Map<String, Object> findFlatById(@Param("id") Integer id);

    @Modifying
    @Query(value = """
        UPDATE incidencias_inasistencias
        SET deleted = TRUE,
            us_deleted = :usuario,
            ts_deleted = NOW()
        WHERE id = :id
        """, nativeQuery = true)
    int softDelete(@Param("id") Integer id, @Param("usuario") Integer usuario);
}
