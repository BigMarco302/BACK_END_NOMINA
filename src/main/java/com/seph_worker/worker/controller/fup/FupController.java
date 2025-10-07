package com.seph_worker.worker.controller.fup;


import com.seph_worker.worker.core.dto.SessionUser;
import com.seph_worker.worker.core.dto.WebServiceResponse;
import com.seph_worker.worker.model.fup.FupConceptoDTO;
import com.seph_worker.worker.service.fup.FupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
@CrossOrigin(origins = "*")
@RequestMapping("/fup/conceptos")
@Tag(name="fup conceptos", description = "/fup/conceptos")
public class FupController {

    private FupService fupService;
    private SessionUser sessionUser;

    @PostMapping("/plaza/{plazaId}/concepto/{conceptoCve}")
    @Operation(summary = "add new fup concepto")
    public WebServiceResponse addFupConcepto(@PathVariable Integer plazaId,
                                             @PathVariable String conceptoCve,
                                             @RequestBody FupConceptoDTO fupConceptoDTO) {
        return fupService.addFupConcepto(plazaId,conceptoCve,fupConceptoDTO,sessionUser.getUser());
    }
}
