package com.seph_worker.worker.model.Empleado;


import lombok.Data;

import java.util.List;

@Data
public class EmployeeDTO {

    private String rfc;
    private String curp;
    private String primerApellido;
    private String segundoApellido;
    private String nombre;


    private Integer qnaini;
    private Integer qnagob;
    private Integer qnasep;

    private String perfil;
    private String nss;

    private Integer catSexoId;
    private Integer catEstadoCivilId;
    private Integer catRegimenId;
    private Integer catTipoContratacionId;
    private Integer nivelAcademicoId;

    private List<Integer> nivel;
    private Boolean activo;

}
