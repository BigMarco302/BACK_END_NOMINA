package com.seph_worker.worker.controller;


import com.seph_worker.worker.core.dto.SessionUser;
import com.seph_worker.worker.core.dto.WebServiceResponse;

import com.seph_worker.worker.core.entity.RoleModuleUser.CoreUser;
import com.seph_worker.worker.core.exception.ResourceNotFoundException;
import com.seph_worker.worker.model.UserDTO;
import com.seph_worker.worker.repository.UserRoleModule.UserRepository;
import com.seph_worker.worker.security.AuthCredentials;
import com.seph_worker.worker.security.TokenUtils;
import com.seph_worker.worker.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
@CrossOrigin(origins = "*")
@RequestMapping("/users")
@Tag(name="Users", description = "/users")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final SessionUser sessionUser;


    @PostMapping("/getToken")
    @Operation(summary = "Obtener Token de sesion por medio de credenciales")
    public ResponseEntity<?> login(@RequestBody AuthCredentials authCredentials) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authCredentials.getUser(), authCredentials.getPassword())
        );

        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        CoreUser user =    userRepository.findOneByUsername(userDetails.getUsername())
                .orElseThrow(()-> new ResourceNotFoundException("No se enontro al usuario"));
        String token = TokenUtils.createToken(user.getId());

        return ResponseEntity.ok(Map.of("token", token));
    }

    @GetMapping("")
    @Operation(summary = "Obtener todos los usuarios")
    public WebServiceResponse getAllUser(){
        return new WebServiceResponse(userService.getAllUsers());
    }
    @GetMapping("/user")
    @Operation(summary = "Obtiene a un usuario")
    public WebServiceResponse getUser(@RequestHeader Integer userId){
        return new WebServiceResponse(userService.getUser(userId));
    }

    @GetMapping("/moduleByUser")
    @Operation(summary = "Obtiene los modulos del usuario")
    public WebServiceResponse getCredentialsByUser(){
        return new WebServiceResponse(userService.getCredentialsByUser(sessionUser.getUser()));
    }

    @PostMapping("/validateToken")
    @Operation(summary = "Validacion de token enviado por correo")
    public WebServiceResponse validateToken(
            @RequestHeader String token){
    return userService.validateToken(token,sessionUser.getUser());
    }

    //POST------------------------------->
    @PostMapping("")
    @Operation(summary = "Crear un nuevo usuario")
    public WebServiceResponse addUser(@RequestBody UserDTO userDTO){
        return (userService.addUser(userDTO));
    }

    @PostMapping("/changePassword")
    @Operation(summary = "Cambio de contraseña del usuario")
    public WebServiceResponse changePassword(
            @RequestHeader("password") String password){
        return (userService.changePassword(password,sessionUser.getUser()));
    }

    @PostMapping("/sendChangePassword")
    @Operation(summary = "Se envia el correo con la liga de cambio de contraseña")
    public WebServiceResponse sendChangePassword(
            @RequestHeader String username
    ) throws MessagingException {return userService.sendEemailRecoveryPassword(username);}

    @GetMapping("/validatePasswordRecovery")
    @Operation(summary = "Validamos si la liga sigue activa")
    public WebServiceResponse changePasswordRecovery(
            @RequestHeader String token,
            @RequestHeader String ts)  {
        return userService.validatePasswordRecovery(token,ts);}

    @PostMapping("/changePasswordRecovery")
    @Operation(summary = "Cambio de contraseña por medio de la liga")
    public WebServiceResponse changePasswordRecovery(
            @RequestHeader String token,
            @RequestHeader String ts,
            @RequestHeader String password
    )  {return userService.changePasswordRecovery(token,ts,password);}



    //UPDATE------------------------------->
    @PatchMapping("")
    @Operation(summary = "Actualizacion de uaurio")
    public WebServiceResponse updateUser(@RequestBody UserDTO userDTO, @RequestHeader Integer userId){return (userService.updateUser(userDTO, userId));}

    @PatchMapping("/softdeleted")
    @Operation(summary = "Eliminacion de un usuario")
    public WebServiceResponse softdeleted( @RequestHeader Integer userId){return (userService.softdeleted( userId));}
}
