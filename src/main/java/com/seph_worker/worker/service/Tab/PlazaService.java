package com.seph_worker.worker.service.Tab;


import com.seph_worker.worker.core.dto.ExcelParserService;
import com.seph_worker.worker.core.dto.WebServiceResponse;
import com.seph_worker.worker.core.entity.Catalogos.CatCct;
import com.seph_worker.worker.core.entity.Fone.FoneAnalitico;
import com.seph_worker.worker.core.entity.Tab.TabPlazas;
import com.seph_worker.worker.core.exception.ResourceNotFoundException;
import com.seph_worker.worker.model.PlazaExelDTO;
import com.seph_worker.worker.repository.Catalogos.CatCctRepository;
import com.seph_worker.worker.repository.Tab.TabPlazasRepository;
import com.seph_worker.worker.repository.fone.FoneAnaliticoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.beans.Transient;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@AllArgsConstructor
public class PlazaService {

    private final TabPlazasRepository tabPlazasRepository;
    private final ExcelParserService excelParserService;
    private final CatCctRepository catCctRepository;
    private final FoneAnaliticoRepository foneAnaliticoRepository;

    public List<TabPlazas> getPlazasByTarget(String target, String targetValue) {
        return tabPlazasRepository.findPlazaByTarget(target, ("%" + targetValue + "%"));
    }

    @Transactional
    public WebServiceResponse addPlazaInAnalitico(MultipartFile file, String folio, String accion) {
        List<PlazaExelDTO> list = null;
        try {
            list = excelParserService.parseExcelToDTO(file, PlazaExelDTO.class);
            Set<String> plazasExistentes = new HashSet<String>();
            Set<String> plazasNoEncontradas = new HashSet<String>();

            switch (accion) {
                case "alta": {
                    list.forEach(plazaExelDTO -> {

                        tabPlazasRepository.findPlazaByPlaza(plazaExelDTO.getCodigoPlaza().replace(" ", ""))
                                .ifPresentOrElse(plaza -> {
                                    plazasExistentes.add(plazaExelDTO.getCodigoPlaza());
                                }, () -> {
                                    plazasNoEncontradas.add(plazaExelDTO.getCodigoPlaza());
                                    TabPlazas tabPlazas = new TabPlazas();
                                    tabPlazas.setTsCreated(new Timestamp(System.currentTimeMillis()));
                                    createFormatPlaza(tabPlazas, folio, plazaExelDTO, true);
                                    tabPlazasRepository.save(tabPlazas);
                                });
                    });
                    return new WebServiceResponse(true, "Se agregaron correctamente las plazas", Map.of("plazasYaExistentes", plazasExistentes,"plazasAgregadas",plazasNoEncontradas));
                }
                case "baja": {
                    list.forEach(plazaExelDTO -> {
                        tabPlazasRepository.findPlazaByPlaza(plazaExelDTO.getCodigoPlaza().replace(" ", ""))
                                .ifPresentOrElse(plaza -> {
                                    plazasExistentes.add(plazaExelDTO.getCodigoPlaza());
                                    createFormatPlaza(plaza, folio, plazaExelDTO, true);
                                    tabPlazasRepository.save(plaza);
                                }, () -> plazasNoEncontradas.add(plazaExelDTO.getCodigoPlaza()));
                    });
                    return new WebServiceResponse(true, "Se actualizaron correctamente las plazas", Map.of("plazasNoEncontradas", plazasNoEncontradas,"plazasActualizadas",plazasExistentes));
                }
                default:
                    throw new ResourceNotFoundException("No se encontro la accion");
            }
        } catch (Exception e) {
            throw new ResourceNotFoundException(e);
        }
    }

    public WebServiceResponse createNewPlazaAnalitico(MultipartFile file) {
        List<FoneAnalitico> list = excelParserService.parseExcelToDTO(file, FoneAnalitico.class);

        foneAnaliticoRepository.deleteAllInBatch();
        foneAnaliticoRepository.saveAll(list);

        return new WebServiceResponse(true, "Se registró correctamente la plaza", list);
    }

    private void createFormatPlaza(TabPlazas plaza, String folio, PlazaExelDTO plazaExelDTO, Boolean isAlta) {
        String plazaAnalitico = plazaExelDTO.getCodigoPlaza().replaceAll(" ", "");
        plaza.setCvePlaza(plazaExelDTO.getCodigoPlaza());
        plaza.setCodPago(plazaAnalitico.substring(0, 2));
        plaza.setUnidad(plazaAnalitico.substring(2, 4));
        plaza.setSubunidad(plazaAnalitico.substring(4, 6));
        plaza.setCategoria(plazaExelDTO.getCat());

        String[] partes = plazaAnalitico.split("\\.");

        String izquierda = partes[0];
        String derecha = partes[1];

        String ultimosDos = izquierda.substring(izquierda.length() - 2);
        String primerDerecha = derecha.substring(0, 1);

        plaza.setHoras(Double.parseDouble((ultimosDos + "." + primerDerecha)));
        plaza.setConsPlaza(Integer.parseInt(plazaAnalitico.substring((plazaAnalitico.length() - 6))));

        Integer quincena = convertLocalDateInQuincena(plazaExelDTO.getFecha());
        plaza.setQnaIni(isAlta ? quincena : 99999);
        plaza.setQnaFin(!isAlta ? quincena : 99999);
        plaza.setFolio(folio);

//        CatCct cct = catCctRepository.findByCveCt(plazaExelDTO.getCct().trim())
//                .orElseThrow(() -> new ResourceNotFoundException("No se encontro el centro de trabajo: " + plazaExelDTO.getCct()));
//
        //plaza.setCatCctId(cct.getId());
        plaza.setCatCctId(1);
        plaza.setFechaFolio(plazaExelDTO.getFecha().atStartOfDay());
        plaza.setCatCodigoCreacionId(2);
        plaza.setCatEstatusPlazaId(isAlta ? 2 : 4);
    }

    private Integer convertLocalDateInQuincena(LocalDate today) {
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        int quincena = (month - 1) * 2 + (day <= 15 ? 1 : 2);
        return Integer.parseInt(String.format("%04d%02d", year, quincena));
    }

}
