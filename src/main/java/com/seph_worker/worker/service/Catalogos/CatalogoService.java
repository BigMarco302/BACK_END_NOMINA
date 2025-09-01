package com.seph_worker.worker.service.Catalogos;


import com.seph_worker.worker.model.EnumClass.CatTypesEnum;
import com.seph_worker.worker.repository.DAOS.CatTypeDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CatalogoService {

    private final CatTypeDAO catTypeDAO;
    private final com.seph_worker.worker.core.dto.toCaseString toCaseString;

    public Object getCatalogoType(CatTypesEnum catType, String whereCondition, String typeCondition, Boolean isDeleted) {
        String typeConditionString = null;
        Integer typeConditionInteger = null;

        return catTypeDAO.getCatType(catType,toCaseString.toSnakeCase(whereCondition),typeCondition,isDeleted);
    }
}
