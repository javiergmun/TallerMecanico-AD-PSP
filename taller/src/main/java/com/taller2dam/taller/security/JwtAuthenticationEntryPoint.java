package com.taller2dam.taller.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taller2dam.taller.errores.ApiError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.security.auth.message.AuthException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper mapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, authException.getMessage());
        String strApiError = mapper.writeValueAsString(apiError);
        PrintWriter writer = response.getWriter();
        writer.println(strApiError);
    }
}
