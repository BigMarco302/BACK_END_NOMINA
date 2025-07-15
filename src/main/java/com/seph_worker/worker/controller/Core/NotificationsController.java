package com.seph_worker.worker.controller.Core;


import com.seph_worker.worker.core.dto.WebServiceResponse;
import com.seph_worker.worker.model.CoreSystem.IconDTO;
import com.seph_worker.worker.service.Core.NotificationsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@CrossOrigin(origins = "*")
@RequestMapping("/notifications")
@Tag(name="Notifications", description = "/notifications")

public class NotificationsController {

    private final NotificationsService notificationsService;

    @GetMapping("")
    @Operation(summary = "Se obtienen todos las notificaciones de un usuario")
    public WebServiceResponse getNotifications(@RequestHeader("userId") Integer userId) {return (notificationsService.getNotificationsByUserId(userId));}

    @PostMapping("")
    @Operation(summary = "Asignar subscripciones de notificaciones a un usuario")
    public WebServiceResponse addNotification(@RequestHeader("userId") Integer userId, @RequestHeader List<Integer> typesIds) {return notificationsService.subcribeUserToNotification(userId, typesIds);}

    @PatchMapping("/status")
    @Operation(summary = "Actualizacion de status de una notificacion")
    public WebServiceResponse changeStatusNotification(
            @RequestHeader("status") Integer status, @RequestHeader("notificationId") Integer notificationId){
        return new WebServiceResponse(notificationsService.changeStatusNotification(status,notificationId));
    }

    @PostMapping("/icons")
    @Operation(summary = "Se agrega un nuevo icono")
    public WebServiceResponse addIcon(@RequestBody IconDTO iconDTO){
        return (notificationsService.addIcon(iconDTO));
    }
}
