package com.seph_worker.worker.service.Catalogos;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CatalogoDireccionesService {

    public List<Map<String,Object>> getCatalogoEntidad(){
        return new ArrayList<>();
    }
}
