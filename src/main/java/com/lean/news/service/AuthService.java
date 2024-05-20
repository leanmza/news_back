package com.lean.news.service;

import com.lean.news.exception.UserNotFound;
import com.lean.news.exception.ValidationException;
import com.lean.news.security.jwt.JwtTokenUtil;
import com.lean.news.service.interfaces.IAuthService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthService implements IAuthService {
    @Autowired
    private UserService userService;

    @Autowired
    JwtTokenUtil tokenUtil;

    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<?> userDetail(Map<String, String> credentials) {
        // Obtener el correo electrónico y la contraseña del cuerpo de la solicitud
        String email = credentials.get("email");
        String password = credentials.get("password");

        try {
            credentialValidation(email, password);
        } catch (ValidationException e) {
            // Crear y devolver una respuesta de error con la clave adecuada
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put(e.getField(), e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }

        UserDetails userDetails = userService.loadUserByUsername(email);

        if (userDetails != null) {

            List<String> roles = userService.getRoleForUser(email);
            //Generar el token JWT
            String jwtToken = tokenUtil.generateToken(email, roles);
            //Crear un objeto Json con el token JWT
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("token", jwtToken);
            responseData.put("userDetails", userDetails);

            return ResponseEntity.ok().header("Authorization", "Bearer " + jwtToken).body(responseData);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo electrónico o contraseña incorrectos");
        }

    }

    @Override
    public void credentialValidation(String email, String password) {
        if (email == null || email.isEmpty()) {
            throw new ValidationException("email", "El correo electrónico no puede estar vacío");
        }
        if (password == null || password.isEmpty()) {
            throw new ValidationException("password", "La contraseña no puede esta vacía");
        }
        EmailValidator validator = EmailValidator.getInstance();
        if (! validator.isValid(email)) {
            throw new ValidationException("email", "Correo electrónico incorrecto");
        }
        if(userService.findByEmail(email).isEmpty()){
            throw new ValidationException("email", "Correo electrónico no registrado");
        }
        if (!passwordEncoder.matches(password, userService.findByEmail(email).get().getPassword())) {
            throw new ValidationException("password", "Contraseña incorrecta");
        }
    }
}
