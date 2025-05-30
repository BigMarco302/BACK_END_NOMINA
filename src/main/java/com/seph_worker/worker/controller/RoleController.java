package com.seph_worker.worker.controller;


import com.seph_worker.worker.core.dto.WebServiceResponse;
import com.seph_worker.worker.model.RoleByUserDTO;
import com.seph_worker.worker.model.RoleDTO;
import com.seph_worker.worker.service.RolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@CrossOrigin(origins = "*")
@RequestMapping("/roles")
@Tag(name="Roles", description = "/roles")
public class RoleController {

    private final RolService rolService;

    @GetMapping("")
    @Operation(summary = "Obetener todos los roles")
    public WebServiceResponse getAllRoles(){
        return new WebServiceResponse(rolService.getAllRoles());
    }

    @GetMapping("/role")
    @Operation(summary = "Obtener un role")
    public WebServiceResponse getRole(@RequestHeader Integer roleId){
        return new WebServiceResponse(rolService.getRole(roleId));
    }

    @GetMapping("/permisos")
    @Operation(summary = "Obtener todos los permisos")
    public WebServiceResponse getPermissions(){
        return new WebServiceResponse(rolService.getPermissions());
    }


    @GetMapping("/permisosById")
    @Operation(summary = "Obtener un permiso")
    public WebServiceResponse permisosById(
            @RequestHeader("permisoId") Integer permisoId){
        return new WebServiceResponse(rolService.permisosById(permisoId));
    }


    //POST-------------------------------------------------------->
    @PostMapping("")
    @Operation(summary = "Agregar un nuevo role")
    public WebServiceResponse addNewRole(@RequestBody RoleDTO roleDTO){
        return (rolService.addNewRole(roleDTO));
    }

    @PostMapping("/roleByUser")
    @Operation(summary = "Asignar roles a un usuario")
    public WebServiceResponse roleByUser(
            @RequestBody List<Integer> rolesId,
            @RequestHeader("userId") Integer userId){
        return (rolService.addRoleByUser(rolesId,userId));
    }

    //UPDATE-------------------------------------------------------->

    @PatchMapping("")
    @Operation(summary = "Actualizacion de un role y sus modulos")
    public WebServiceResponse updateRole(@RequestBody RoleDTO roleDTO, @RequestHeader Integer roleId){
        return (rolService.updateRole(roleDTO,roleId));
    }

    @PatchMapping("/softdeleted")
    @Operation(summary = "Se elimina un role")
    public WebServiceResponse softdeleted(@RequestHeader Integer roleId){
        return (rolService.softdeleted(roleId));
    }
}
