package com.seph_worker.worker.security;

import com.seph_worker.worker.core.entity.RoleModuleUser.CoreUser;
import com.seph_worker.worker.repository.UserRoleModule.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final CoreUser user;
    private final List<Integer> roles;
    private RoleRepository rolRepository;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return user.getPass();
    }

    public CoreUser getUserDTO() {
        return user;
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public Integer getUserId() {
        return user.getId();
    }

    public Map<String,Object> permisos(){
        Map<String,Object> permisos = new HashMap<>();


        getALlRoles().forEach(roleId -> {
        Map<String,Object> permisos2 = new HashMap<>();
            permisos2=   rolRepository.getAllPermisos(roleId);
            permisos.put(permisos2.get("name").toString(),Integer.parseInt(permisos2.get("permiso").toString()));

        });
        return permisos;
    }

    public Map<String, Object> getConfig() {
        return user.getConfig();
    }

//    public Map<String, Object> getOrganization() {
//        if(user.getOrganizationId() == null) {
//            return Map.of("id",0,"name","");
//        }else{
//            Organization org = user.getOrganization();
//            return Map.of("id",org.getId(),"name",org.getName());
//        }
//    }


    public List<Integer> getALlRoles() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }


}
