package com.seph_worker.worker.repository.Catalogos;


import com.seph_worker.worker.core.entity.Catalogos.Empleado.CatNivelAcademico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatNivelAcademicoRepository extends JpaRepository<CatNivelAcademico, Integer> {
}
