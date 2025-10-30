package com.seph_worker.worker.service;


import com.seph_worker.worker.core.dto.PatchUtils;
import com.seph_worker.worker.core.dto.WebServiceResponse;
import com.seph_worker.worker.core.entity.Core.RoleModuleUser.CoreUser;
import com.seph_worker.worker.core.entity.NomBeneficiariosAlim;
import com.seph_worker.worker.core.entity.TabBeneficiariosAlim;
import com.seph_worker.worker.model.BeneficiarioDTO;
import com.seph_worker.worker.model.BeneficiariosAlimDTO;
import com.seph_worker.worker.repository.BeneficiariosRepository;
import com.seph_worker.worker.repository.TabBeneficiariosAlimRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@AllArgsConstructor
public class BeneficiariosService {

    private final BeneficiariosRepository beneficiariosRepository;
    private final TabBeneficiariosAlimRepository tabBeneficiariosRepository;


    public WebServiceResponse addNewBeneAlim(BeneficiariosAlimDTO dto, CoreUser user) {
        TabBeneficiariosAlim ali = new TabBeneficiariosAlim();
        ali.setRfc(dto.getRfc());
        ali.setPrimerApellido(dto.getPrimerApellido());
        ali.setSegundoApellido(dto.getSegundoApellido());
        ali.setNombre(dto.getNombre());
        ali.setTsCreated(new Timestamp(System.currentTimeMillis()));
        ali.setUsCreated(user.getId());
        tabBeneficiariosRepository.save(ali);
        return new WebServiceResponse(true, "Se guardo correctamente el registro", Map.of("id",ali.getId()));
    }

    public WebServiceResponse updateBeneAlim(Integer bene, BeneficiariosAlimDTO dto, CoreUser user) {
        TabBeneficiariosAlim ali = tabBeneficiariosRepository.findById(bene)
                .orElseThrow(() -> new ResourceAccessException("No se encontro el dato"));

        PatchUtils.copyNonNullProperties(dto, ali);
        ali.setUsModified(user.getId());
        ali.setTsModified(new Timestamp(System.currentTimeMillis()));

        tabBeneficiariosRepository.save(ali);
        return new WebServiceResponse(true, "Se actualizo el registro");
    }

    public WebServiceResponse softdeleteBeneAlim(Integer bene, CoreUser user) {
        TabBeneficiariosAlim ali = tabBeneficiariosRepository.findById(bene)
                .orElseThrow(() -> new ResourceAccessException("No se encontro el dato"));

        ali.setTsDeleted(Timestamp.valueOf(LocalDateTime.now()));
        ali.setUsDeleted(user.getId());
        ali.setDeleted(true);
        tabBeneficiariosRepository.save(ali);
        return new WebServiceResponse(true, "Se actualizo el registro");
    }


    public WebServiceResponse addNewBene(BeneficiarioDTO dto, CoreUser user) {
        NomBeneficiariosAlim ali = new NomBeneficiariosAlim();
        ali.setTabEmpleadosId(dto.getTabEmpleadosId());
        ali.setTabBeneficiariosAlimId(dto.getTabBeneficiariosAlimId());
        ali.setFormaAplicacion(dto.getFormaAplicacion());
        ali.setFactorImporte(dto.getFactorImporte());
        ali.setNumeroBenef(dto.getNumeroBenef());
        ali.setQnaini(dto.getQnaini());
        ali.setQnafin(dto.getQnafin());
        ali.setNumeroDocumento(dto.getNumeroDocumento());
        ali.setFechaDocumento(dto.getFechaDocumento());
        ali.setTsCreated(new Timestamp(System.currentTimeMillis()));
        ali.setUsCreated(user.getId());
        beneficiariosRepository.save(ali);
        return new WebServiceResponse(true, "Se guardo correctamente el registro");
    }

    public WebServiceResponse updateBene(Integer bene, BeneficiarioDTO dto, CoreUser user) {
        NomBeneficiariosAlim ali = beneficiariosRepository.findById(bene)
                .orElseThrow(() -> new ResourceAccessException("No se encontro el dato"));

        PatchUtils.copyNonNullProperties(dto, ali);
        beneficiariosRepository.save(ali);
        return new WebServiceResponse(true, "Se actualizo el registro");
    }

    public WebServiceResponse softdeleteBene(Integer bene, CoreUser user) {
        NomBeneficiariosAlim ali = beneficiariosRepository.findById(bene)
                .orElseThrow(() -> new ResourceAccessException("No se encontro el dato"));

        ali.setTsDeleted(Timestamp.valueOf(LocalDateTime.now()));
        ali.setUsDeleted(user.getId());
        ali.setDeleted(true);
        beneficiariosRepository.save(ali);
        return new WebServiceResponse(true, "Se actualizo el registro");
    }

    public Object getAllBene() {
        return beneficiariosRepository.findAll();
    }

    public Object getBene(Integer bene) {
        return beneficiariosRepository.getBeneficiarioId(bene);
    }

}
