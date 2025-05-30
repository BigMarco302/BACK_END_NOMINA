package com.seph_worker.worker.controller;


import com.seph_worker.worker.core.dto.WebServiceResponse;
import com.seph_worker.worker.model.ModulosDTO;
import com.seph_worker.worker.service.ModuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
@CrossOrigin(origins = "*")
@RequestMapping("/modules")
@Tag(name="Modules", description = "/modules")
public class ModuleController {

    private final ModuleService moduleService;

    @GetMapping("")
    @Operation(summary = "Obtener todos los modulos")
    public WebServiceResponse getAllModules(){
        return new WebServiceResponse(moduleService.getAllModules());
    }

    @GetMapping("/module")
    @Operation(summary = "Obtener un modulo")
    public WebServiceResponse getModule(@RequestHeader Integer moduleId){return new WebServiceResponse(moduleService.getModule(moduleId)); }


    //POST-------------------------------------------------------->
    @PostMapping("")
    @Operation(summary = "Agregar un nuevo modulo y sus roles")
    public WebServiceResponse addModule(@RequestBody ModulosDTO modulosDTO){
        return (moduleService.addModule(modulosDTO));
    }

    //UPDATE-------------------------------------------------------->
    @PatchMapping("")
    @Operation(summary = "Actualizar un modulo y sus roles")
    public WebServiceResponse updateModule(@RequestBody ModulosDTO modulosDTO, @RequestHeader Integer moduleId){
        return (moduleService.updateModule(modulosDTO, moduleId));
    }
    @PatchMapping("/softdeleted")
    @Operation(summary = "Eliminacion de un modulo")
    public WebServiceResponse softdeleted(@RequestHeader Integer moduleId){
        return (moduleService.softdeleted( moduleId));
    }
}
