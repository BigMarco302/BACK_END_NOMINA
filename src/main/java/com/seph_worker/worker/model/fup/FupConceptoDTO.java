package com.seph_worker.worker.model.fup;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class FupConceptoDTO {
    private String acuerdo;
    private Integer qnaIni;
    private Integer qnaFin;
    private BigDecimal importe;

}
