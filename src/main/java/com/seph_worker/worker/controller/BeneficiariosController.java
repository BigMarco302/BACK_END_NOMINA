package com.seph_worker.worker.controller;


import com.seph_worker.worker.core.dto.SessionUser;
import com.seph_worker.worker.core.dto.WebServiceResponse;
import com.seph_worker.worker.model.BeneficiarioDTO;
import com.seph_worker.worker.model.BeneficiariosAlimDTO;
import com.seph_worker.worker.service.BeneficiariosService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@CrossOrigin(origins = "*")
@RequestMapping("/beneficiarios")
@Tag(name = "beneficiarios", description = "/beneficiarios")
public class BeneficiariosController {

    private final SessionUser sessionUser;
    private final BeneficiariosService beneficiariosService;

    @PostMapping("")
    public WebServiceResponse addBene(@RequestBody BeneficiarioDTO dto) {

        return beneficiariosService.addNewBene(dto, sessionUser.getUser());
    }

    @PatchMapping("/{beneficiarioId}")
    public WebServiceResponse updateBene(@RequestBody BeneficiarioDTO dto,
                                         @PathVariable Integer beneficiarioId) {
        return beneficiariosService.updateBene(beneficiarioId, dto, sessionUser.getUser());
    }

    @DeleteMapping("/{beneficiarioId}")
    public WebServiceResponse softdeleteBene(@PathVariable Integer beneficiarioId) {
        return beneficiariosService.softdeleteBene(beneficiarioId, sessionUser.getUser());
    }

    @GetMapping("/{beneficiarioId}")
    public WebServiceResponse getBene(@PathVariable Integer beneficiarioId) {
        return new WebServiceResponse( beneficiariosService.getBene(beneficiarioId));
    }

    @GetMapping("")
    public WebServiceResponse getAllBene() {
        return new WebServiceResponse(beneficiariosService.getAllBene());
    }


    //-----------
    @PostMapping("/alim")
    public WebServiceResponse addBeneAlim(@RequestBody BeneficiariosAlimDTO dto) {
        return beneficiariosService.addNewBeneAlim(dto, sessionUser.getUser());
    }

    @PatchMapping("/alim/{beneficiarioId}")
    public WebServiceResponse updateBeneAlim(@RequestBody BeneficiariosAlimDTO dto,
                                         @PathVariable Integer beneficiarioId) {
        return beneficiariosService.updateBeneAlim(beneficiarioId, dto, sessionUser.getUser());
    }

    @DeleteMapping("/alim/{beneficiarioId}")
    public WebServiceResponse softdeleteBeneAlim(@PathVariable Integer beneficiarioId) {
        return beneficiariosService.softdeleteBeneAlim(beneficiarioId, sessionUser.getUser());
    }
}
