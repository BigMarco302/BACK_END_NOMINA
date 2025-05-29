package com.seph_worker.worker.controller;


import com.seph_worker.worker.core.dto.WebServiceResponse;

import com.seph_worker.worker.model.UserDTO;
import com.seph_worker.worker.security.TokenUtils;
import com.seph_worker.worker.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
@CrossOrigin(origins = "*")
@RequestMapping("/users")
@Tag(name="Users", description = "/users")
public class UserController {

    private final UserService userService;


    @GetMapping("")
    @Operation(summary = "Obtener todos los usuarios")
    public WebServiceResponse getAllUser(){
        return new WebServiceResponse(userService.getAllUsers());
    }
    @GetMapping("/user")
    public WebServiceResponse getUser(@RequestHeader Integer userId){
        return new WebServiceResponse(userService.getUser(userId));
    }

    @GetMapping("/moduleByUser")
    public WebServiceResponse getCredentialsByUser(
            @RequestHeader Integer userId){
        return new WebServiceResponse(userService.getCredentialsByUser(userId));
    }

    @PostMapping("/validateToken")
    public WebServiceResponse validateToken(
            @RequestHeader("Authorization") String barer,
            @RequestHeader String token){
        Integer userId =  TokenUtils.getUserFromToken(barer);
    return userService.validateToken(token,userId);
    }

    //POST------------------------------->
    @PostMapping("")
    public WebServiceResponse addUser(@RequestBody UserDTO userDTO){
        return (userService.addUser(userDTO));
    }

    @PostMapping("/changePassword")
    public WebServiceResponse changePassword(
            @RequestHeader("Authorization") String barer,
            @RequestHeader("password") String password){
        Integer userId =  TokenUtils.getUserFromToken(barer);
        return (userService.changePassword(password,userId));
    }

    @PostMapping("/sendChangePassword")
    public WebServiceResponse sendChangePassword(
            @RequestHeader String username
    ) throws MessagingException {return userService.sendEemailRecoveryPassword(username);}

    @GetMapping("/validatePasswordRecovery")
    public WebServiceResponse changePasswordRecovery(
            @RequestHeader String token,
            @RequestHeader String ts)  {
        return userService.validatePasswordRecovery(token,ts);}

    @PostMapping("/changePasswordRecovery")
    public WebServiceResponse changePasswordRecovery(
            @RequestHeader String token,
            @RequestHeader String ts,
            @RequestHeader String password
    )  {return userService.changePasswordRecovery(token,ts,password);}



    //UPDATE------------------------------->
    @PatchMapping("")
    public WebServiceResponse updateUser(@RequestBody UserDTO userDTO, @RequestHeader Integer userId){return (userService.updateUser(userDTO, userId));}

    @PatchMapping("/softdeleted")
    public WebServiceResponse softdeleted( @RequestHeader Integer userId){return (userService.softdeleted( userId));}
}
