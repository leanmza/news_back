package com.lean.news.controller;

import com.lean.news.model.entity.User;
import com.lean.news.security.jwt.JwtTokenUtil;
import com.lean.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;
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
    JwtTokenUtil tokenUtil;


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

        // Obtener el correo electrónico y la contraseña del cuerpo de la solicitud
        String email = credentials.get("email");
        String password = credentials.get("password");

        if (email == null || password == null || email.isEmpty() || password.isEmpty()){
            return ResponseEntity.badRequest().body("Correo electrónico o contraseña no proporcionados");
        }

        UserDetails userDetails = userService.loadUserByUsername(email);

        if(userDetails != null) {

            List<String> roles = userService.getRoleForUser(email);
            //Generar el token JWT

            String jwtToken = tokenUtil.generateToken(email, roles);

            //Crear un objeto Json con el token JWT
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("token", jwtToken);
            responseData.put("userDetails", userDetails);

            return ResponseEntity.ok(responseData);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo electrónico o contraseña incorrectos");
        }


    }


}