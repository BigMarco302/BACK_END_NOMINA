package com.seph_worker.worker.controller.Tab;


import com.seph_worker.worker.core.dto.SessionUser;
import com.seph_worker.worker.core.dto.WebServiceResponse;
import com.seph_worker.worker.model.Empleado.EmployeeDTO;
import com.seph_worker.worker.service.Tab.EmpleadoService;
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
@RequestMapping("/employee")
@Tag(name="employee", description = "/employee")
public class EmpleadoController {

    private final EmpleadoService empleadoService;
    private final SessionUser sessionUser;

        @GetMapping("/by/{target}")
    @Operation(summary = "Get alls employees base")
    public WebServiceResponse getEmployeesByTarget(@PathVariable @Schema(allowableValues = {"RFC", "CURP", "NOMBRE"}) String target,
                                                  @RequestHeader String targetValue) {
        return new WebServiceResponse(empleadoService.getEmployeesByTarget(target,targetValue));
    }

    @PostMapping("")
    @Operation(summary = "create a new employee in RH")
    public WebServiceResponse createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return empleadoService.createEmployee(employeeDTO,sessionUser.getUser());
    }

    @PatchMapping("")
    @Operation(summary = "update a employee in RH")
    public WebServiceResponse updateEmployee(@RequestBody EmployeeDTO employeeDTO, @RequestHeader Integer employeeId) {
        return empleadoService.updateEmployee(employeeDTO,employeeId,sessionUser.getUser());
    }

    @PatchMapping("/softdelete")
    @Operation(summary = "softdelete a employee in RH")
    public WebServiceResponse softdeleteEmployee(@RequestHeader Integer employeeId) {
        return empleadoService.softdeleteEmployee(employeeId,sessionUser.getUser());
    }

    @GetMapping("/contratacionId")
    @Operation(summary = "Get alls employees by tipo contratacion")
    public WebServiceResponse getAllEmployees(@RequestHeader int page,
                                              @RequestHeader int size,
                                              @RequestHeader Integer catTipoContratacionId) {
        return new WebServiceResponse(empleadoService.getAllEmployees(page, size,catTipoContratacionId));
    }

    @GetMapping("/base")
    @Operation(summary = "Get alls employees base")
    public WebServiceResponse getAllEmployeesBase(@RequestHeader int page,
                                              @RequestHeader int size) {
        return new WebServiceResponse(empleadoService.getAllEmployeesBase(page, size));
    }

}
