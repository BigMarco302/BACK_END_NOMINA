package com.seph_worker.worker.controller.clabes;

import com.seph_worker.worker.core.dto.SessionUser;
import com.seph_worker.worker.core.dto.WebServiceResponse;
import com.seph_worker.worker.model.clabes.TabClabeDTO;
import com.seph_worker.worker.service.clabe.TabClabesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
@CrossOrigin(origins = "*")
@RequestMapping("/clabes")
@Tag(name="clabes", description = "/clabes")
public class TabClabesController {

    private final TabClabesService tabClabesService;
    private final SessionUser  sessionUser;

    @PostMapping()
    @Operation(summary = "Add new clabe")
    public WebServiceResponse addClabe(@RequestBody TabClabeDTO dto){
        return tabClabesService.addClabe(dto,sessionUser.getUser());
    }

    @GetMapping("/catClabe/{catClabeId}")
    @Operation(summary = "Get all calbes by clabeId")
    public WebServiceResponse getAllClabes(@PathVariable("catClabeId") Integer catClabeId){
        return new WebServiceResponse(tabClabesService.getClabes(catClabeId));
    }

        @GetMapping("/catClabe/{catClabeId}/target/{targetId}")
    @Operation(summary = "Get all calbes by clabeId")
    public WebServiceResponse getAllClabesByTarget(@PathVariable("catClabeId") Integer catClabeId,
                                            @PathVariable("targetId") Integer targetId){
        return new WebServiceResponse(tabClabesService.getClabesByTarget(catClabeId,targetId));
    }

        @GetMapping("/{clabeId}/catClabe/{catClabeId}")
    @Operation(summary = "Get all calbes by clabeId AND clabeId")
    public WebServiceResponse getAllClabesById(@PathVariable("catClabeId") Integer catClabeId,
                                            @PathVariable("clabeId") Integer clabeId){
        return new WebServiceResponse(tabClabesService.getClabesById(catClabeId,clabeId));
    }
}
