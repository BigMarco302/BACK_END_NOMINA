package com.seph_worker.worker.repository.Catalogos;


import com.seph_worker.worker.core.entity.Catalogos.Empleado.CatDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatDocumentoRepository extends JpaRepository<CatDocumento, Integer>{
}
