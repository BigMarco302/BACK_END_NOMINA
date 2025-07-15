package com.seph_worker.worker.service.Core;

import com.seph_worker.worker.core.dto.EncryptionUtils;
import com.seph_worker.worker.core.dto.WebServiceResponse;
import com.seph_worker.worker.core.entity.RoleModuleUser.CoreModules;
import com.seph_worker.worker.core.entity.RoleModuleUser.CoreUser;
import com.seph_worker.worker.core.entity.RoleModuleUser.CoreUserRole;
import com.seph_worker.worker.core.exception.ResourceNotFoundException;
import com.seph_worker.worker.model.CoreSystem.UserDTO;
import com.seph_worker.worker.repository.Core.TokensVerifyRepository;
import com.seph_worker.worker.repository.Core.UserRoleModule.ModuleRepository;
import com.seph_worker.worker.repository.Core.UserRoleModule.UserRepository;
import com.seph_worker.worker.repository.Core.UserRoleModule.UserRoleRepository;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
@Log4j2
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private ModuleRepository moduleRepository;
    private UserRoleRepository userRoleRepository;
    private TokensVerifyRepository tokensVerifyRepository;
    private RolService rolService;
    private EmailSystemService emailSystemService;

    public List<CoreUser> getAllUsers(){
        return userRepository.findAll();
    }

    public CoreUser getUser(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro al usuario"));
    }

    public List<Map<String, Object>> getCredentialsByUser(CoreUser user) {
        List<Map<String, Object>> modules = new ArrayList<>();
       List<Integer> extras = (List<Integer>) user.getConfig().get("extras");
       List<Integer> rolIds = userRepository.getRolesIdByUser(user.getId());
        if (extras == null) {
            extras = new ArrayList<>();
        }
        CoreModules module = moduleRepository.findByName("Salir");
        if(module != null){
            extras.add(module.getId());
        }
        modules.addAll(userRepository.getCredentialsByUser(rolIds));
        modules.addAll(userRepository.getCredentialsByModule(extras));

        return  modules;
    }

    @Transactional
    public WebServiceResponse validateToken (String token,CoreUser user){

        Map<String,Object> userToken  = new HashMap<>(tokensVerifyRepository.getTokenCurrent(user.getId()));

        if(userToken == null || userToken.isEmpty()) throw new ResourceNotFoundException("No se encontro token");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        LocalDateTime tsCreated = LocalDateTime.parse(userToken.get("ts_created").toString(), formatter);
        Duration duration = Duration.between(tsCreated, LocalDateTime.now());

        if(userToken.get("token").toString().equals(token) && !duration.isNegative() && duration.toMinutes() <= 5){
            user.setIsVerified(TRUE);
            userRepository.save(user);
            return new WebServiceResponse(true,"Cuenta verificada");
        }else{
           return new WebServiceResponse(false,"El token invalido o expirado");
        }

    }
    public WebServiceResponse sendEemailRecoveryPassword(String username) throws MessagingException {
        CoreUser user = userRepository.findOneByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro al usuario"));

        String token = EncryptionUtils.encrypt(user.getId().toString());
        String timestamp =  EncryptionUtils.encrypt(String.valueOf(Instant.now().toEpochMilli()));

        String liga = "https://tusistema.com/recuperar?folio=" + token + "&ts=" + timestamp;
        emailSystemService.sendHtmlToken("sistema",user.getEmail(),"Recuperacion de contraseña","recoveryPassword",Map.of("{{name}}","Marco Vazquez","{{RECOVERY_LINK}}",liga),null);
        return new WebServiceResponse(true, "Se envio un correo de recuperacion de contraseña");
    }
    @Transactional
    public WebServiceResponse changePasswordRecovery(String token, String time, String password) {
        Integer userId = Integer.parseInt(EncryptionUtils.decrypt(token));
        long ts = Long.parseLong(EncryptionUtils.decrypt(time));

        CoreUser user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        long now = Instant.now().toEpochMilli();
        long minutos = Duration.ofMillis(now - ts).toMinutes();

        if (minutos > 15 || minutos < 0) {
            return new WebServiceResponse(false, "La liga ha expirado");
        }
        user.setPass(new BCryptPasswordEncoder().encode(password));
        userRepository.save(user);

        return new WebServiceResponse(true, "La contraseña se ha restablecido la correctamente.");
    }

    @Transactional
    public WebServiceResponse validatePasswordRecovery(String token, String time) {
        Integer userId = Integer.parseInt(EncryptionUtils.decrypt(token));
        long ts = Long.parseLong(EncryptionUtils.decrypt(time));

        userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        long now = Instant.now().toEpochMilli();
        long minutos = Duration.ofMillis(now - ts).toMinutes();

        if (minutos > 15 || minutos < 0) {
            return new WebServiceResponse(false, "La liga ha expirado");
        }
        return new WebServiceResponse(true, "Liga esta vigente");
    }


    public WebServiceResponse changePassword(String password,CoreUser user){
        if(!user.getIsPassword() ){
            user.setIsPassword(TRUE);
            user.setPass(new BCryptPasswordEncoder().encode(password));
            userRepository.save(user);
            return new WebServiceResponse(true,"Contraseña actualizada");
        }else{
            return new WebServiceResponse(false,"OCURRIO UN ERROR");
        }
    }


    @Transactional
    public WebServiceResponse addUser(UserDTO dto){

        try {
            CoreUser newUser = new CoreUser();
            Map<String,Object> config = new HashMap<>();
            config.put("principal", dto.getPrincipal());
            config.put("extras", dto.getExtras());

            newUser.setConfig(Map.of("config", config));
            newUser.setCatEmpleadoId(dto.getSrl_emp());
            newUser.setUsername(dto.getUser());
            newUser.setPass(new BCryptPasswordEncoder().encode(dto.getPassword()));
            newUser.setArea(dto.getArea());
            newUser.setTask(dto.getTask());
            newUser.setActive(dto.getActive());
            newUser.setDeleted(FALSE);
//            if(dto.getOrganizationId() == 0 ){
//                newUser.setOrganizationId(null);
//            }else{
//                organizationRepository.findById(dto.getOrganizationId()).orElseThrow(()-> new ResourceNotFoundException("No se encontro el organizacion"));
//                newUser.setOrganizationId(dto.getOrganizationId());
//            }
            userRepository.save(newUser);
//            if(!dto.getNotifications().isEmpty()) {
//                notificationsService.subcribeUserToNotification(newUser.getId(), dto.getNotifications());
//            }
            rolService.addRoleByUser(dto.getRoles(),newUser.getId());
            return new WebServiceResponse(true, "Se agrego correctamente el user", "id", newUser.getId());
        } catch (Exception e) {
            throw new RuntimeException("Error updating report: " + e.getMessage());
        }
    }

    @Transactional
    public WebServiceResponse softdeleted(Integer userId) {
        CoreUser user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("No se encontro al usuario"));
        user.setDeleted(TRUE);
        userRepository.save(user);
        return new WebServiceResponse(true, "Se elimino el usuario: "+user.getUsername());
    }

    @Transactional
    public WebServiceResponse updateUser(UserDTO dto, Integer userId){
        CoreUser oldUser = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("No se encontro el usuario"));
        Map<String,Object> config = new HashMap<>();
        config.put("principal", dto.getPrincipal());
        config.put("extras", dto.getExtras());

        oldUser.setConfig(Map.of("config", config));
        oldUser.setCatEmpleadoId(dto.getSrl_emp());
        oldUser.setArea(dto.getArea());
        oldUser.setTask(dto.getTask());
        oldUser.setActive(dto.getActive());
//        organizationRepository.findById(dto.getOrganizationId()).orElseThrow(()-> new ResourceNotFoundException("No se encontro el organizacion"));
//        oldUser.setOrganizationId(dto.getOrganizationId());


        List<Map<String,Integer>> currentRoles = userRoleRepository.getRolesByUser(userId);
        currentRoles.forEach(role->{
            Integer roleId = role.get("roleId");
            if(!dto.getRoles().contains(roleId)){
                softDeletedRoleUser(role.get("id"));
            }
            dto.getRoles().remove(roleId);
        });
        rolService.addRoleByUser(dto.getRoles(),userId);
        return new WebServiceResponse("Se actualizo correctamente el usuario");
    }

    private void softDeletedRoleUser(Integer roleUserId){
        CoreUserRole userRole = userRoleRepository.findById(roleUserId).orElseThrow(()->new ResourceNotFoundException("No se encontro el Rol"));
        userRole.setDeleted(TRUE);
        userRoleRepository.save(userRole);
    }
}
