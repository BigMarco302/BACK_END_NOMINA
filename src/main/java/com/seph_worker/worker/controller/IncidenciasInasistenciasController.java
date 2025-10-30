package com.seph_worker.worker.controller;

import com.seph_worker.worker.core.dto.SessionUser;
import com.seph_worker.worker.core.dto.WebServiceResponse;
import com.seph_worker.worker.model.IncidenciasInasistenciasDTO;
import com.seph_worker.worker.service.IncidenciasInasistenciasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequiredArgsConstructor
@Log4j2
@CrossOrigin(origins = "*")
@RequestMapping("/inasistencias")
@Tag(name = "inasistencias", description = "/inasistencias")
public class IncidenciasInasistenciasController {

    private final IncidenciasInasistenciasService incidenciasInasistenciasService;
    private final SessionUser sessionUser;

    @PostMapping
    @Operation(summary = "Registrar nueva inasistencia")
    public WebServiceResponse addIncidencia(@RequestBody IncidenciasInasistenciasDTO dto) {
        return incidenciasInasistenciasService.addIncidencia(dto, sessionUser.getUser());
    }

    @GetMapping("")
    @Operation(summary = "Consultar inasistencias con filtros opcionales")
    public WebServiceResponse getIncidencia(
            @RequestParam(required = false) Long empleadoId,
            @RequestParam(required = false) Integer qnaProceso,
            @RequestParam(required = false) LocalDate desde,
            @RequestParam(required = false) LocalDate hasta,
            @RequestParam(required = false) String tipo
    ){
        return new WebServiceResponse(
                incidenciasInasistenciasService.getIncidencias(empleadoId, qnaProceso, desde, hasta, tipo)
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar (soft delete) una inasistencia por ID")
    public WebServiceResponse deleteIncidencia(@PathVariable Integer id){
        return incidenciasInasistenciasService.deleteIncidencia(id, sessionUser.getUser());
    }

}
