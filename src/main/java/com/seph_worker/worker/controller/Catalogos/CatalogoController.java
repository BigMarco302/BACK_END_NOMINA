package com.seph_worker.worker.controller.Catalogos;


import com.seph_worker.worker.core.dto.SessionUser;
import com.seph_worker.worker.core.dto.WebServiceResponse;
import com.seph_worker.worker.model.EnumClass.CatTypesEnum;
import com.seph_worker.worker.service.Catalogos.CatalogoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @GetMapping("/{catalogoType}")
    @Operation(summary = "get a dinamic catalog")
    public WebServiceResponse createEmployee(@PathVariable @Schema(allowableValues =
                                            {"sexo", "estado_civil", "nivel_academico", "tipo_contratcion",
                                            "regimen", "documentos", "clabes", "tipo_domicilio", "bancos"}) String catalogoType,
                                             @RequestHeader (required = false) String whereCondition,
                                             @RequestHeader (required = false) Object typeCondition) {
        return new WebServiceResponse(catalogoService.getCatalogoType(CatTypesEnum.from(catalogoType),whereCondition, typeCondition));
    }


}
