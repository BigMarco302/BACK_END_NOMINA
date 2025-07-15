package com.seph_worker.worker.core.dto;

import com.seph_worker.worker.core.entity.RoleModuleUser.CoreUser;
import com.seph_worker.worker.core.exception.ResourceNotFoundException;
import com.seph_worker.worker.repository.Core.UserRoleModule.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SessionUser {

    private final UserRepository userRepository;

    public CoreUser getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = null;

        if (authentication != null && authentication.getPrincipal() != null) {
            userId = Integer.parseInt(authentication.getPrincipal().toString());
        }
        if (userId == null) throw new ResourceNotFoundException( "Usuario no autenticado");

        CoreUser usr = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("Usuario no encontrado"));

        return usr;
    }

}
