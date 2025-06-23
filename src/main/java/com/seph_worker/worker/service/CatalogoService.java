package com.seph_worker.worker.service;


import com.seph_worker.worker.repository.Cat.CatEstadoCivilRepository;
import com.seph_worker.worker.repository.Cat.CatNivelAcademicoRepository;
import com.seph_worker.worker.repository.Cat.CatRegimenRepository;
import com.seph_worker.worker.repository.Cat.CatSexoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CatalogoService {

    private CatSexoRepository catSexoRepository;
    private CatEstadoCivilRepository estadoCivilRepository;
    private CatRegimenRepository regimenRepository;
    private CatNivelAcademicoRepository nivelAcademicoRepository;

    public Map<String,Object> getCatalogosEmployee() {
        Map<String,Object> map = new HashMap<>();
        map.put("catSexo",catSexoRepository.findAll());
        map.put("catEstadoCivil",estadoCivilRepository.findAll());
        map.put("catRegimen",regimenRepository.findAll());
        map.put("catNivelAcademico",nivelAcademicoRepository.findAll());
        return map;
    }
}
