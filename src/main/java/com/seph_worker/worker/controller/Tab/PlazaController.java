package com.seph_worker.worker.controller.Tab;


import com.seph_worker.worker.core.dto.SessionUser;
import com.seph_worker.worker.core.dto.WebServiceResponse;
import com.seph_worker.worker.service.Tab.PlazaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Log4j2
@CrossOrigin(origins = "*")
@RequestMapping("/plazas")
@Tag(name = "plazas", description = "/plazas")
public class PlazaController {

    private final SessionUser sessionUser;
    private final PlazaService plazaService;

    @GetMapping("/by/{target}")
    @Operation(summary = "Get alls plazas")
    public WebServiceResponse getEmployeesByTarget(@PathVariable @Schema(allowableValues = {"PLAZA", "CATEGORIA", "CCT_ID"}) String target,
                                                   @RequestHeader String targetValue) {
        return new WebServiceResponse(plazaService.getPlazasByTarget(target, targetValue));
    }

    @PostMapping("/analitico")
    @Operation(summary = "create a new plaza")
    public WebServiceResponse createNewPlazaAnalitico(@RequestParam("file") MultipartFile file){
        return plazaService.createNewPlazaAnalitico(file);
    }

    @PostMapping("/fone/{accion}/{folio}")
    @Operation(summary = "Guarda datos de Alta o Baja de exel")
    public WebServiceResponse addPlazaInAnalitico(@RequestParam("file") MultipartFile file,
                                                  @PathVariable String folio,
                                                  @PathVariable @Schema(allowableValues = {"alta","baja"}) String accion){
        return plazaService.addPlazaInAnalitico(file,folio,accion);
    }



}
