package com.seph_worker.worker.repository.Catalogos.Direcciones;


import com.seph_worker.worker.core.entity.Catalogos.Direcciones.CatEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatEntidadRepository extends JpaRepository<CatEntidad, Integer> {
}
