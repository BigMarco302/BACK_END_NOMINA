package com.seph_worker.worker.service;


import com.seph_worker.worker.repository.Fup.FupConceptosRepository;
import com.seph_worker.worker.repository.Fup.FupMovsRepository;
import com.seph_worker.worker.repository.Fup.FupMtosRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FupService {

    private final FupConceptosRepository fupConceptosRepository;
    private final FupMovsRepository fupMovsRepository;
    private final FupMtosRepository fupMtosRepository;
}
