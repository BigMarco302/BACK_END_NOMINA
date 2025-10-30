package com.seph_worker.worker.service;

import com.seph_worker.worker.core.dto.PatchUtils;
import com.seph_worker.worker.core.dto.WebServiceResponse;
import com.seph_worker.worker.core.entity.Core.RoleModuleUser.CoreUser;
import com.seph_worker.worker.core.entity.IncidenciasInasistencias;
import com.seph_worker.worker.core.entity.Tab.Empleados.TabEmpleado;
import com.seph_worker.worker.core.entity.Tab.TabPlazas;
import com.seph_worker.worker.core.exception.ResourceNotFoundException;
import com.seph_worker.worker.model.IncidenciasInasistenciasDTO;
import com.seph_worker.worker.repository.IncidenciasInasistenciasRepository;
import com.seph_worker.worker.repository.Tab.EmpleadoRepository.TabEmpleadoRepository;
import com.seph_worker.worker.repository.Tab.TabPlazasRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@AllArgsConstructor
public class IncidenciasInasistenciasService {
    private final IncidenciasInasistenciasRepository incidenciasInasistenciasRepository;
    private final TabEmpleadoRepository tabEmpleadoRepository;
    private final TabPlazasRepository tabPlazasRepository;

    @Transactional
    public void incidencias(IncidenciasInasistenciasDTO dto, Integer id){
           IncidenciasInasistencias incidencias = incidenciasInasistenciasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Incidencia no encontrada"));
        PatchUtils.copyNonNullProperties(dto,incidencias);
        incidencias.setTsModified(Timestamp.valueOf(LocalDateTime.now()));
                incidenciasInasistenciasRepository.save(incidencias);
    }
    @Transactional
    public WebServiceResponse addIncidencia(IncidenciasInasistenciasDTO dto, CoreUser user) {

        tabEmpleadoRepository.findById(Math.toIntExact(dto.getTabEmpleadosId()))
                .orElseThrow(()-> new ResourceNotFoundException("No se encontró el empleado: "+dto.getTabEmpleadosId()));

        tabPlazasRepository.findById(Math.toIntExact(dto.getTabPlazasId()))
                .orElseThrow(()-> new ResourceNotFoundException(("No se encontró la plaza: "+dto.getTabPlazasId())));



        IncidenciasInasistencias incidencias = new IncidenciasInasistencias();
        incidencias.setFolio(dto.getFolio());
        incidencias.setEjercicio(dto.getEjercicio());
        incidencias.setQnaProceso(dto.getQnaProceso());
        incidencias.setFechaInasistencia(dto.getFechaInasistencia());
        incidencias.setTipoInasistencia(dto.getTipoInasistencia());
        incidencias.setHorasInasistencia(dto.getHorasInasistencia());
        TabEmpleado empleado = tabEmpleadoRepository.findById(Math.toIntExact(dto.getTabEmpleadosId()))
                .orElseThrow(() -> new ResourceNotFoundException("número de empleado no encontrado"));
        incidencias.setTabEmpleado(empleado);
        TabPlazas plaza = tabPlazasRepository.findById(Math.toIntExact(dto.getTabPlazasId()))
                .orElseThrow(() -> new ResourceNotFoundException("Plaza no encontrada"));
        incidencias.setTabPlazas(plaza);
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

    public Object getIncidencias(Long empleadoId, Integer qnaProceso, LocalDate desde,LocalDate hasta, String tipo) {
        return incidenciasInasistenciasRepository.findAllFlat(empleadoId, qnaProceso, desde, hasta, tipo);
    }

    @Transactional
    public WebServiceResponse deleteIncidencia(Integer id, CoreUser user) {
        IncidenciasInasistencias incidencias = incidenciasInasistenciasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Incidencia no encontrada"));

        incidencias.setDeleted(true);
        incidencias.setUsDeleted(user.getId());
        incidencias.setTsDeleted(new Timestamp(System.currentTimeMillis()));
        incidenciasInasistenciasRepository.save(incidencias);

        return new WebServiceResponse(true, "Inasistencia eliminada", Map.of("id", id));
    }

}
