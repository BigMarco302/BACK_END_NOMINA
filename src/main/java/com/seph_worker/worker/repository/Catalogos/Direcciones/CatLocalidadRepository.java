package com.seph_worker.worker.repository.Catalogos.Direcciones;

import com.seph_worker.worker.core.entity.Catalogos.Direcciones.CatLocalidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatLocalidadRepository extends JpaRepository<CatLocalidad, Integer> {
    Optional<List<CatLocalidad>> findByCveEntAndCveMun(String cveEnt, String cveMun);
}
