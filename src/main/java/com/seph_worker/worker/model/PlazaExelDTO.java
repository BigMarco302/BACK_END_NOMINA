package com.seph_worker.worker.model;


import com.seph_worker.worker.core.dto.ExcelColumn;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PlazaExelDTO {

    @ExcelColumn(index = 0)
    private String ramo;

    @ExcelColumn(index = 1)
    private String ur;

    @ExcelColumn(index = 2)
    private LocalDate fecha;

    @ExcelColumn(index = 3)
    private String cat;

    @ExcelColumn(index = 4)
    private Integer mod;

    @ExcelColumn(index = 5)
    private Integer ze;

    @ExcelColumn(index = 6)
    private String niv;

    @ExcelColumn(index = 7)
    private String pH;

    @ExcelColumn(index = 8)
    private String codigoPlaza;

    @ExcelColumn(index = 9)
    private String cct;

    @ExcelColumn(index = 10)
    private String bC;

    @ExcelColumn(index = 11)
    private Integer cant;

    @ExcelColumn(index = 12)
    private String estatus;

    @ExcelColumn(index = 13)
    private Double costoUnitario;

}
