package com.seph_worker.worker.controller;


import com.seph_worker.worker.core.dto.SessionUser;
import com.seph_worker.worker.core.dto.WebServiceResponse;
import com.seph_worker.worker.model.Empleado.EmployeeDTO;
import com.seph_worker.worker.service.CatalogoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
@CrossOrigin(origins = "*")
@RequestMapping("/catalogo")
@Tag(name="catalogo", description = "/catalogo")
public class CatalogoController {

    private final CatalogoService catalogoService;
    private final SessionUser sessionUser;

    @GetMapping("/catEmployee")
    @Operation(summary = "get catalogo of employee module")
    public WebServiceResponse createEmployee() {
        return new WebServiceResponse(catalogoService.getCatalogosEmployee());
    }
}
