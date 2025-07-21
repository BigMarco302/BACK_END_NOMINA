package com.seph_worker.worker.controller.Catalogos;


import com.seph_worker.worker.core.dto.WebServiceResponse;
import com.seph_worker.worker.service.Catalogos.CatalogoDireccionesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
@CrossOrigin(origins = "*")
@RequestMapping("/catalogo/direcciones")
@Tag(name="catalogo de direcciones", description = "/catalogo/direcciones")
public class CatalogoDireccionesController {

    private final CatalogoDireccionesService catalogoDireccionesService;

    @GetMapping("/entidad")
    @Operation(summary = "get a dinamic catalog address")
    public WebServiceResponse getCatalogoEntidad() {
        return new WebServiceResponse(catalogoDireccionesService.getCatalogoEntidad());
    }

    @GetMapping("/municipio")
    @Operation(summary = "get a dinamic catalog address")
    public WebServiceResponse getCatalogoMunicipio(@RequestHeader String cveEnt) {
        return new WebServiceResponse(catalogoDireccionesService.getCatalogoMunicipio(cveEnt));
    }

    @GetMapping("/localidad")
    @Operation(summary = "get a dinamic catalog address")
    public WebServiceResponse getLocalidadMunicipio(@RequestHeader String cveEnt,
                                                    @RequestHeader String cveMun) {
        return new WebServiceResponse(catalogoDireccionesService.getCatalogoLocalidad(cveEnt,cveMun));
    }
}
