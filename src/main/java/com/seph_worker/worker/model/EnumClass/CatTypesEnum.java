package com.seph_worker.worker.model.EnumClass;


import com.seph_worker.worker.core.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum CatTypesEnum {
    SEXO("cat_sexo"),
    ESTADO_CIVIL("cat_estado_civil"),
    NIVEL_ACADEMICO("cat_nivel_academico"),
    TIPO_CONTRATCION("cat_tipo_contratacion"),
    REGIMEN("cat_regimen"),
    DOCUMENTOS("cat_documentos"),
    CLABES("cat_clabes"),
    TIPO_DOMICILIO("cat_tipo_domicilio"),
    CAT_CCT("cat_cct"),
    BANCOS("cat_bancos");

    public static final Map<String, CatTypesEnum> BY_NAME = new HashMap<>();

    static {
        for (CatTypesEnum e : values()) {
            BY_NAME.put(e.name(), e);

        }
    }

    public final String value;

    @Override
    public String toString() {
        return getValue();
    }


    public static CatTypesEnum from(String name) {
        if (BY_NAME.containsKey(name.toUpperCase()))
            return BY_NAME.get(name.toUpperCase());

        throw new ResourceNotFoundException("No se encontro el tipo: " + name);
    }
}
