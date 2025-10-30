package com.seph_worker.worker.model;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BeneficiarioDTO {
    private Integer tabEmpleadosId;
    private Integer tabBeneficiariosAlimId;
    private Character formaAplicacion;
    private BigDecimal factorImporte;
    private Integer numeroBenef;
    private Integer qnaini;
    private Integer qnafin;
    private String numeroDocumento;
}
