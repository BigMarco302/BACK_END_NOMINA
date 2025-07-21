package com.seph_worker.worker.controller;


import com.seph_worker.worker.core.dto.SessionUser;
import com.seph_worker.worker.service.FupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
@CrossOrigin(origins = "*")
@RequestMapping("/fup")
@Tag(name="fup", description = "/fup")
public class FupController {

    private FupService fupService;
    private SessionUser sessionUser;
}
