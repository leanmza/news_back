package com.lean.news.controller;

import com.lean.news.model.entity.User;
import com.lean.news.service.AuthService;
import com.lean.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.Optional;

@RestController


@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
//@CrossOrigin(origins = "**", allowCredentials = "true")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @GetMapping("/user/me")
    public Optional<User> getCurrentUser() {
        // Obtener la autenticación actual desde el contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Obtener el nombre de usuario del objeto de autenticación
        String email = authentication.getName();

        // Utilizar el servicio de usuario para obtener la información del usuario
        return userService.findByEmail(email);
    }


    @PostMapping("/loginCheck")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) throws GeneralSecurityException, IOException {
        return authService.userDetail(credentials);

    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        // Invalidar la sesión para eliminar la información del usuario
        userService.logout();
        // Redirigir al usuario a la página de inicio de sesión o a otra página según tu aplicación
        return ResponseEntity.noContent().build();
    }


}