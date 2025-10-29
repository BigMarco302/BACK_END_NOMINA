package com.seph_worker.worker.service;

import com.seph_worker.worker.core.dto.WebServiceResponse;
import com.seph_worker.worker.core.entity.Core.RoleModuleUser.CoreUser;
import com.seph_worker.worker.core.entity.IncidenciasInasistencias;
import com.seph_worker.worker.model.IncidenciasInasistenciasDTO;
import com.seph_worker.worker.repository.IncidenciasInasistenciasRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Map;

@Service
@AllArgsConstructor
public class IncidenciasInasistenciasService {
    private final IncidenciasInasistenciasRepository incidenciasInasistenciasRepository;

    @Transactional
    public WebServiceResponse addIncidencia(IncidenciasInasistenciasDTO dto, CoreUser user) {

        if (dto.getTabEmpleadosId() == null) throw new IllegalArgumentException("Falta id de empleado");
        if (dto.getTabPlazasId() == null) throw new IllegalArgumentException("Falta id de plaza");
        if (dto.getFechaInasistencia() == null) throw new IllegalArgumentException("Falta fecha de inasistencia");
        if (dto.getTipoInasistencia() == null || dto.getTipoInasistencia().isBlank()) throw new IllegalArgumentException("Falta tipo de inasistencia");

        IncidenciasInasistencias incidencias = new IncidenciasInasistencias();
        incidencias.setFolio(dto.getFolio());
        incidencias.setEjercicio(dto.getEjercicio());
        incidencias.setQnaProceso(dto.getQnaProceso());
        incidencias.setFechaInasistencia(dto.getFechaInasistencia());
        incidencias.setTipoInasistencia(dto.getTipoInasistencia());
        incidencias.setHorasInasistencia(dto.getHorasInasistencia());
        incidencias.setTabEmpleadosId(dto.getTabEmpleadosId());
        incidencias.setTabPlazasId(dto.getTabPlazasId());
        incidencias.setDeleted(false);
        incidencias.setUsCreated(user.getId());
        incidencias.setTsCreated(new Timestamp(System.currentTimeMillis()));
        incidenciasInasistenciasRepository.save(incidencias);

        return new WebServiceResponse(
                true,
                "Inasistencia registrada",
                Map.of("id", incidencias.getId())
        );

    }

    @Transactional
    public Object getIncidencias(Long empleadoId, Integer qnaProceso, LocalDate desde,LocalDate hasta, String tipo) {
        return incidenciasInasistenciasRepository.findAllFlat(empleadoId, qnaProceso, desde, hasta, tipo);
    }

    @Transactional
    public WebServiceResponse deleteIncidencia(Long id, CoreUser user) {
        int updated = incidenciasInasistenciasRepository.softDelete(id, user.getId());
        if (updated == 0) {
            throw new IllegalArgumentException("No se encontro el inasistencia");
        }
        return new WebServiceResponse(true, "Inasistencia eliminada", Map.of("id", id));
    }

}
