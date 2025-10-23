package com.seph_worker.worker.service.clabe;

import com.seph_worker.worker.core.dto.WebServiceResponse;
import com.seph_worker.worker.core.entity.Core.RoleModuleUser.CoreUser;
import com.seph_worker.worker.core.entity.Tab.Empleados.TabClabes;
import com.seph_worker.worker.model.clabes.TabClabeDTO;
import com.seph_worker.worker.repository.Catalogos.CatBancoRepository;
import com.seph_worker.worker.repository.Catalogos.CatClabeRepository;
import com.seph_worker.worker.repository.Tab.EmpleadoRepository.TabClabesRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Map;

@Service
@AllArgsConstructor
public class TabClabesService {

    private final TabClabesRepository tabClabesRepository;
    private final CatBancoRepository catBancoRepository;
    private final CatClabeRepository catClabeRepository;

    public WebServiceResponse addClabe(TabClabeDTO dto, CoreUser user) {
        catClabeRepository.findById(dto.getCatClabeId())
                .orElseThrow(() -> new IllegalArgumentException("No se encontro el catClabe"));
        catBancoRepository.findById(dto.getCatBancoId())
                .orElseThrow(() -> new IllegalArgumentException("No se encontro el banco"));
        TabClabes clabe = new TabClabes();
        clabe.setClabe(dto.getClabe());
        clabe.setCatClabeId(dto.getCatClabeId());
        clabe.setTargetId(dto.getTargetId());
        clabe.setCatBancoId(dto.getCatBancoId());
        clabe.setEstatus(dto.getEstatus());
        clabe.setDeleted(false);
        clabe.setUsCreated(user.getId());
        clabe.setTsCreated(new Timestamp(System.currentTimeMillis()));
        tabClabesRepository.save(clabe);
        return new WebServiceResponse(true, "Se guardo correctamente la clabe: " + clabe.getClabe(), Map.of("id", clabe.getId()));
    }

    public Object getClabes(Integer catClabeId) {
        catClabeRepository.findById(catClabeId)
                .orElseThrow(() -> new IllegalArgumentException("No se encontro el catClabe"));
        return tabClabesRepository.getClabes(catClabeId);
    }

    public Object getClabesById(Integer catClabeId, Integer clabeId) {
        catClabeRepository.findById(catClabeId)
                .orElseThrow(() -> new IllegalArgumentException("No se encontro el catClabe"));
        tabClabesRepository.findById(clabeId)
                .orElseThrow(() -> new IllegalArgumentException("No se encontro el clabe"));
        return tabClabesRepository.getClabesById(catClabeId, clabeId);
    }

    public Object getClabesByTarget(Integer catClabeId, Integer targetId) {
        catClabeRepository.findById(catClabeId)
                .orElseThrow(() -> new IllegalArgumentException("No se encontro el catClabe"));
        return tabClabesRepository.getClabesByTarget(catClabeId, targetId);
    }
}
