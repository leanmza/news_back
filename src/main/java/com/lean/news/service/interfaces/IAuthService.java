package com.lean.news.service.interfaces;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IAuthService {

    ResponseEntity<?> userDetail(Map<String, String> credentials);

    void credentialValidation(String email, String password);


}
