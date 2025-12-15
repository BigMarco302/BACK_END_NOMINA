package com.seph_worker.worker.controller;


import com.seph_worker.worker.core.dto.WebServiceResponse;
import com.seph_worker.worker.service.CalculationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.Get;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@CrossOrigin(origins = "*")
@RequestMapping("/calculation")
@Tag(name = "calculation", description = "/calculation")
public class CalculationController {


    private final CalculationService calculationService;

    @GetMapping("")
    @Operation(summary = "Get calculation nomina")
    public WebServiceResponse getCalculation(@RequestHeader Integer qnaProceso,
                                             @RequestHeader(required = false) Integer nivelSueldo,
                                             @RequestHeader(required = false) List<String> concepto,
                                             @RequestHeader(required = false) Integer empleadoId) {
        return new WebServiceResponse(calculationService.getCalculation(qnaProceso, nivelSueldo, concepto,empleadoId));
    }

}
