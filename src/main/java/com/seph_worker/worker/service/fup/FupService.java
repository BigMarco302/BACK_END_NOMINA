package com.seph_worker.worker.service.fup;


import com.seph_worker.worker.core.dto.WebServiceResponse;
import com.seph_worker.worker.core.entity.Core.RoleModuleUser.CoreUser;
import com.seph_worker.worker.core.entity.Fup.FupConceptos;
import com.seph_worker.worker.core.exception.ResourceNotFoundException;
import com.seph_worker.worker.model.fup.FupConceptoDTO;
import com.seph_worker.worker.repository.Fup.FupConceptosRepository;
import com.seph_worker.worker.repository.Fup.FupMovsRepository;
import com.seph_worker.worker.repository.Fup.FupMtosRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class FupService {

    private final FupConceptosRepository fupConceptosRepository;
    private final FupMovsRepository fupMovsRepository;
    private final FupMtosRepository fupMtosRepository;


    @Transactional
    public WebServiceResponse addFupConcepto(Integer plazaId, String conceptoCve, FupConceptoDTO dto, CoreUser user){
        try {
            FupConceptos fc = new FupConceptos();
            fc.setQnaFin(dto.getQnaFin());
            fc.setQnaIni(dto.getQnaIni());
            fc.setImporte(dto.getImporte());
            fc.setCatConceptoCve(conceptoCve);
            fc.setCatPlazaId(plazaId);
            fupConceptosRepository.save(fc);
            return new WebServiceResponse(true, "Se agrego correctamente el concepto: "+conceptoCve);
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
