package com.seph_worker.worker.repository;


import com.seph_worker.worker.core.entity.TabBeneficiariosAlim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TabBeneficiariosAlimRepository extends JpaRepository<TabBeneficiariosAlim, Integer> {

}
