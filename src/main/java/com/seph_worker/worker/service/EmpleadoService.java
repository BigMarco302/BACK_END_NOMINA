package com.seph_worker.worker.service;


import com.seph_worker.worker.core.dto.PatchUtils;
import com.seph_worker.worker.core.dto.WebServiceResponse;
import com.seph_worker.worker.core.entity.Empleados.TabEmpleado;
import com.seph_worker.worker.core.entity.RoleModuleUser.CoreUser;
import com.seph_worker.worker.core.exception.ResourceNotFoundException;
import com.seph_worker.worker.model.Empleado.EmployeeDTO;
import com.seph_worker.worker.repository.Cat.*;
import com.seph_worker.worker.repository.EmpleadoRepository.TabEmpleadoRepository;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.standard.PageRanges;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class EmpleadoService {

    private TabEmpleadoRepository tabEmpleadoRepository;
    private CatEstadoCivilRepository catEstadoCivilRepository;
    private CatNivelAcademicoRepository catNivelAcademicoRepository;
    private CatRegimenRepository catRegimenRepository;
    private CatSexoRepository catSexoRepository;
    private CatTipoContratacionRepository catTipoContratacionRepository;

    public Map<String,Object> getAllEmployees(int page,int size){
        Pageable pages = PageRequest.of(page,size);
        Map<String,Object> map = new HashMap<>();

        map.put("base",tabEmpleadoRepository.findEmpleaadosBase(pages));
        map.put("otros",tabEmpleadoRepository.findEmpleaadosNotBase(pages));
        return map;
    }
    @Transactional
    public WebServiceResponse updateEmployee(EmployeeDTO dto,Integer employeeId, CoreUser user){
        TabEmpleado employee = tabEmpleadoRepository.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not found"));
        validateIdsByEmployee(dto.getCatSexoId(),dto.getCatEstadoCivilId(),dto.getCatRegimenId(),dto.getCatTipoContratacionId(),dto.getNivelAcademicoId());

        try {
            PatchUtils.copyNonNullProperties(dto, employee);
            employee.setTsModified(new Timestamp(System.currentTimeMillis()));
            employee.setUsModified(user.getId());
            tabEmpleadoRepository.save(employee);
            return new WebServiceResponse(true, "Se actualizo correctamente el empleado: "+employee.getNombre()+" "+employee.getSegundoApellido());
        } catch (Exception e) {
            throw new RuntimeException("Ocurrio un error al actualizar el empleado: "+e);
        }
    }
    @Transactional
    public WebServiceResponse softdeleteEmployee(Integer employeeId, CoreUser user){
        TabEmpleado employee = tabEmpleadoRepository.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not found"));

        try {
            employee.setDeleted(Boolean.TRUE);
            employee.setUsDeleted(user.getId());
            employee.setTsDeleted(new Timestamp(System.currentTimeMillis()));
            tabEmpleadoRepository.save(employee);
            return new WebServiceResponse(true,"Se elimino correctamente el empleado: "+employee.getNombre()+" "+employee.getSegundoApellido());
        } catch (Exception e) {
            throw new RuntimeException("Ocurrio un error al eliminar al empleado: +"+e);
        }
    }

    private void validateIdsByEmployee(Integer catSexoId,Integer catEstadoCivilId, Integer catRegimenId , Integer catTipoContratacionId , Integer nivelAcademicoId){
        catEstadoCivilRepository.findById(catEstadoCivilId)
                .orElseThrow(() -> new ResourceNotFoundException("Estado Civil not found"));

        catNivelAcademicoRepository.findById(nivelAcademicoId)
                .orElseThrow(() -> new ResourceNotFoundException("Nivel Academico not found"));

        catRegimenRepository.findById(catRegimenId)
                .orElseThrow(() -> new ResourceNotFoundException("Regimen not found"));

        catSexoRepository.findById(catSexoId)
                .orElseThrow(() -> new ResourceNotFoundException("Sexo not found"));

        catTipoContratacionRepository.findById(catTipoContratacionId)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo contratacion not found"));
    }
    @Transactional
    public WebServiceResponse createEmployee(EmployeeDTO dto, CoreUser user){

        validateIdsByEmployee(dto.getCatSexoId(),dto.getCatEstadoCivilId(),dto.getCatRegimenId(),dto.getCatTipoContratacionId(),dto.getNivelAcademicoId());

        try {
            TabEmpleado em = new TabEmpleado();
            em.setId(null);
            em.setRfc(dto.getRfc());
            em.setCurp(dto.getCurp());
            em.setPrimerApellido(dto.getPrimerApellido());
            em.setSegundoApellido(dto.getSegundoApellido());
            em.setNombre(dto.getNombre());
            em.setQnaini(dto.getQnaini());
            em.setQnagob(dto.getQnagob());
            em.setQnasep(dto.getQnasep());
            em.setPerfil(dto.getPerfil());
            em.setNss(dto.getNss());

            em.setNivel(dto.getNivel());
            em.setCatSexoId(dto.getCatSexoId());
            em.setCatEstadoCivilId(dto.getCatEstadoCivilId());
            em.setCatRegimenId(dto.getCatRegimenId());
            em.setCatTipoContratacionId(dto.getCatTipoContratacionId());
            em.setNivelAcademicoId(dto.getNivelAcademicoId());

            em.setActivo(dto.getActivo());
            em.setUsCreated(user.getId());
            em.setTsCreated(new Timestamp(System.currentTimeMillis()));
            em.setDeleted(Boolean.FALSE);
            tabEmpleadoRepository.save(em);
            return new WebServiceResponse(true,"Se creo correctamente el empleado: "+em.getNombre()+" "+em.getSegundoApellido());
        } catch (Exception e) {
            throw new RuntimeException("Ocurrio un error al crear al empleado: "+e);
        }
    }
}
