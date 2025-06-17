package com.seph_worker.worker.controller;


import com.seph_worker.worker.core.dto.SessionUser;
import com.seph_worker.worker.core.dto.WebServiceResponse;
import com.seph_worker.worker.model.Empleado.EmployeeDTO;
import com.seph_worker.worker.service.EmpleadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
@CrossOrigin(origins = "*")
@RequestMapping("/employee")
@Tag(name="employee", description = "/employee")
public class EmpleadoController {

    private final EmpleadoService empleadoService;
    private final SessionUser sessionUser;

    @PostMapping("")
    @Operation(summary = "create a new employee in RH")
    public WebServiceResponse createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return empleadoService.createEmployee(employeeDTO,sessionUser.getUser());
    }

    @GetMapping("")
    @Operation(summary = "Get alls employees by tipo contratacion")
    public WebServiceResponse getAllEmployees(@RequestHeader Integer tipoContratacionId) {
        return new WebServiceResponse(empleadoService.getAllEmployees(tipoContratacionId));
    }

}
