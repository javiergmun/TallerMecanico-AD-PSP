package com.taller2dam.taller.security.jwt;

import com.taller2dam.taller.dao.Usuario;
import com.taller2dam.taller.dao.users.UserRole;
import com.taller2dam.taller.dto.UsuarioDTO;
import com.taller2dam.taller.mapper.UsuarioMapper;
import com.taller2dam.taller.security.jwt.JwtProvider;
import com.taller2dam.taller.security.jwt.model.JwtUserResponse;
import com.taller2dam.taller.security.jwt.model.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider tokenProvider;
    private final UsuarioMapper usuarioMapper;


    @PostMapping("/auth/login")
    public ResponseEntity<JwtUserResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Usuario user = (Usuario) authentication.getPrincipal();
        String jwtToken = tokenProvider.generateToken(authentication);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertUserToUserResponse(user, jwtToken));
    }

    public UsuarioDTO me(@AuthenticationPrincipal Usuario user) {
        return usuarioMapper.toDTO(user);
    }

    private JwtUserResponse convertUserToUserResponse(Usuario user, String jwtToken) {
        return JwtUserResponse
                .jwtUserResponseBuilder()
                .dni(user.getDni())
                .correo(user.getCorreo())
                .username(user.getUsername())
                .telefono(user.getTelefono())
                .roles(user.getRoles().stream().map(UserRole::name).collect(Collectors.toSet()))
                .token(jwtToken)
                .build();

    }


}
