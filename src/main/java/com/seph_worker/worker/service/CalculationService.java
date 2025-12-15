package com.seph_worker.worker.service;


import com.seph_worker.worker.repository.CalculationDAO;
import com.seph_worker.worker.repository.CalculationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CalculationService {

    private final CalculationDAO calculationDAO;


    public Object getCalculation (Integer qnaProceso, Integer nivelSueldo, List<String> conceptos, Integer empleadoId){
        return calculationDAO.getCalculation(qnaProceso, nivelSueldo, conceptos, empleadoId);
    }
}
