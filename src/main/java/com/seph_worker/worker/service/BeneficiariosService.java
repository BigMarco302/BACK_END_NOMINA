package com.seph_worker.worker.service;


import com.seph_worker.worker.core.dto.PatchUtils;
import com.seph_worker.worker.core.dto.WebServiceResponse;
import com.seph_worker.worker.core.entity.Core.RoleModuleUser.CoreUser;
import com.seph_worker.worker.core.entity.NomBeneficiariosAlim;
import com.seph_worker.worker.model.BeneficiarioDTO;
import com.seph_worker.worker.repository.BeneficiariosRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class BeneficiariosService {

    private final BeneficiariosRepository beneficiariosRepository;


    public WebServiceResponse addNewBene(BeneficiarioDTO dto, CoreUser user){
        NomBeneficiariosAlim ali = new  NomBeneficiariosAlim();
        ali.setTabEmpleadosId(dto.getTabEmpleadosId());
        ali.setTabBeneficiariosAlimId(dto.getTabBeneficiariosAlimId());
        ali.setCatCctId(dto.getCatCctId());
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
        return new  WebServiceResponse(true,"Se guardo correctamente el registro");
    }

    public WebServiceResponse updateBene(Integer bene,BeneficiarioDTO dto, CoreUser user){
         NomBeneficiariosAlim ali = beneficiariosRepository.findById(bene)
                 .orElseThrow(()-> new ResourceAccessException("No se encontro el dato"));

        PatchUtils.copyNonNullProperties(dto,ali);
        beneficiariosRepository.save(ali);
        return  new  WebServiceResponse(true,"Se actualizo el registro");
    }

     public WebServiceResponse softdeleteBene(Integer bene, CoreUser user){
         NomBeneficiariosAlim ali = beneficiariosRepository.findById(bene)
                 .orElseThrow(()-> new ResourceAccessException("No se encontro el dato"));

        ali.setTsDeleted(Timestamp.valueOf(LocalDateTime.now()));
        ali.setUsDeleted(user.getId());
        ali.setDeleted(true);
        beneficiariosRepository.save(ali);
        return  new  WebServiceResponse(true,"Se actualizo el registro");
    }

    public Object getAllBene(){
        return  beneficiariosRepository.findAll();
    }

    public Object getBene(Integer bene){
         NomBeneficiariosAlim ali = beneficiariosRepository.findById(bene)
                 .orElseThrow(()-> new ResourceAccessException("No se encontro el dato"));
         return ali;
    }

}
