package com.seph_worker.worker.model;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BeneficiarioDTO {
    private Integer tabEmpleadosId;
    private Integer tabBeneficiariosAlimId;
    private String formaAplicacion;
    private BigDecimal factorImporte;
    private Integer numeroBenef;
    private Integer qnaini;
    private Integer qnafin;
    private String numeroDocumento;

    public Character getFormaAplicacion(){
        return formaAplicacion.toCharArray()[0];
    }

}
