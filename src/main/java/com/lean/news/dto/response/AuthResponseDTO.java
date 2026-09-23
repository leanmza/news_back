package com.lean.news.dto.response;

public record AuthResponseDTO(String username, String message, String jwt, boolean status) {
}
