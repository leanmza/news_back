package com.lean.news.security.jwt;

import com.lean.news.model.entity.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtProperties jwtProperties;

    public JwtFilter(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = extractToken(request, filterChain, response);
        if (token == null) {
            return;
        }
        try {
            Claims claims = processJwtToken(token);
            setAuthentication(claims);
        } catch (MalformedJwtException e) {
            handleJwtError(response, "Token JWT mal formado o ausente");
            return;
        } catch (JwtException e) {
            handleJwtError(response, e.getMessage());
            return;

        }
        filterChain.doFilter(request,response);

    }

    private void handleJwtError(HttpServletResponse response, String errorMessage) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"message\": \"" + errorMessage + "\"}");
    }

    private void setAuthentication(Claims claims) {
        if (claims.getSubject() != null){
            Object authoritiesObj = claims.get("authorities");
            if (authoritiesObj instanceof List<?> list) {
                List<SimpleGrantedAuthority>authorities = list.stream()
                        .filter(obj -> obj instanceof String)
                        .map(obj -> new SimpleGrantedAuthority((String) obj))
                        .toList();

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
    }

    private Claims processJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    private String extractToken(HttpServletRequest request, FilterChain filterChain, HttpServletResponse response) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return null;
        }
        return header.replace("Bearer", "");
    }
}
