package com.seph_worker.worker.service.Catalogos;


import com.seph_worker.worker.core.entity.Catalogos.Direcciones.CatEntidad;
import com.seph_worker.worker.core.entity.Catalogos.Direcciones.CatLocalidad;
import com.seph_worker.worker.core.entity.Catalogos.Direcciones.CatMunicipio;
import com.seph_worker.worker.core.exception.ResourceNotFoundException;
import com.seph_worker.worker.repository.Catalogos.Direcciones.CatEntidadRepository;
import com.seph_worker.worker.repository.Catalogos.Direcciones.CatLocalidadRepository;
import com.seph_worker.worker.repository.Catalogos.Direcciones.CatMunicipioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CatalogoDireccionesService {

    private final CatEntidadRepository catEntidadRepository;
    private final CatMunicipioRepository catMunicipioRepository;
    private final CatLocalidadRepository catLocalidadRepository;

    public List<CatEntidad> getCatalogoEntidad(){
        return catEntidadRepository.findAll();
    }

    public List<CatMunicipio> getCatalogoMunicipio(String cveEnt){
        return catMunicipioRepository.findByCveEnt(cveEnt)
                .orElseThrow(()-> new ResourceNotFoundException("No se encontro el municipio"));
    }

    public List<CatLocalidad> getCatalogoLocalidad(String cveEnt, String cveMun){
        return catLocalidadRepository.findByCveEntAndCveMun(cveEnt, cveMun)
                .orElseThrow(()-> new ResourceNotFoundException("No se encontro la localidad"));
    }
}
