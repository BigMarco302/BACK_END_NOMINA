package com.seph_worker.worker.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seph_worker.worker.core.dto.WebServiceResponse;
import com.seph_worker.worker.core.entity.RoleModuleUser.CoreUser;
import com.seph_worker.worker.core.entity.CoreTokensVerify;
import com.seph_worker.worker.repository.TokensVerifyRepository;
import com.seph_worker.worker.service.EmailSystemService;
import jakarta.mail.MessagingException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@AllArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final EmailSystemService emailSystemService;
    private final TokensVerifyRepository tokensVerifyRepository;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        AuthCredentials credentials;
        try {
            credentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse authentication request body", e);
        }

        UsernamePasswordAuthenticationToken userPAT = new UsernamePasswordAuthenticationToken(
                credentials.getUser(),
                credentials.getPassword(),
                Collections.emptyList()
        );

        return getAuthenticationManager().authenticate(userPAT);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        String token = TokenUtils.createToken(userDetails.getUserId());

        // Configurar la respuesta con el token y JSON personalizado
        response.addHeader("Authorization", token);
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.setContentType("application/json");


        response.setStatus(HttpServletResponse.SC_OK);

        Map<String, Object> dataUser = new HashMap<>();
        String messageWebService = "Autenticación exitosa";
        CoreUser user = userDetails.getUserDTO();
        dataUser.put("userId", user.getId());
        dataUser.put("verified", user.getIsVerified());
        dataUser.put("isPassword", user.getIsPassword());
        dataUser.put("permisos", userDetails.permisos());
        dataUser.put("roles", userDetails.getALlRoles());

        if (!user.getIsVerified()) {
            String tokenEmail = generateToken();
            messageWebService = "Se envio un codigo de verificacion";
            try {
                CoreTokensVerify tok = new CoreTokensVerify();
                tok.setToken(tokenEmail);
                tok.setUserId(user.getId());
                tok.setTs_created(Timestamp.valueOf(LocalDateTime.now()));
                tok.setDeleted(Boolean.FALSE);
                tokensVerifyRepository.save(tok);
                emailSystemService.sendHtmlToken("sistema", user.getEmail(), "Token de validacion", "token", Map.of("{{TOKEN}}",tokenEmail,"{{name}}","Marco Vazquez"), null);
            } catch (MessagingException e) {}

            dataUser.put("config", Map.of("principal", "pages/Extras/Verifycode", "extras", List.of(11)));
        } else if (!user.getIsPassword()) {
            dataUser.put("config", Map.of("principal", "pages/Extras/changePassword", "extras", List.of(11)));
        } else {
            dataUser.put("config", user.getConfig().get("config"));
        }

        WebServiceResponse successResponse = new WebServiceResponse(true, messageWebService, dataUser);

        ObjectMapper mapper = new ObjectMapper();
        try (PrintWriter out = response.getWriter()) {
            mapper.writeValue(out, successResponse);
        }
    }

    public static String generateToken() {
        String chars = "0123456789";
        java.security.SecureRandom random = new java.security.SecureRandom();
        char[] token = new char[6];
        for (int i = 0; i < 6; i++) {
            token[i] = chars.charAt(random.nextInt(chars.length()));
        }
        return new String(token);
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        WebServiceResponse errorResponse;

        if (failed instanceof UsernameNotFoundException) {
            errorResponse = new WebServiceResponse(false, failed.getMessage());
        } else if (failed instanceof BadCredentialsException) {
            errorResponse = new WebServiceResponse(false, "La contraseña es incorrecta");
        } else {
            errorResponse = new WebServiceResponse(false, "Los datos ingresados son incorrectos");
        }

        ObjectMapper mapper = new ObjectMapper();
        try (PrintWriter out = response.getWriter()) {
            mapper.writeValue(out, errorResponse);
        }
    }
}
