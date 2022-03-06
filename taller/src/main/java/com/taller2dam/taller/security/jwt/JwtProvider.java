package com.taller2dam.taller.security.jwt;

import com.taller2dam.taller.dao.Usuario;
import com.taller2dam.taller.dao.users.UserRole;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Log
@Component
public class JwtProvider {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";

    @Value("${jwt.secret:EsteEsUnSecretoDeEjemploQueSePodriaSustituirCuandoSeQuiera")
    private String secreto;

    @Value("${jwt.token-expiration:864000}")
    private int jwtDurationTokenEnSegundos;

    public String generateToken(Authentication authentication) {
        //Esta información será la que se guarde en el cuerpo del jwt
        Usuario user = (Usuario) authentication.getPrincipal();
        Date tokenExpirationDate = new Date(System.currentTimeMillis() + (jwtDurationTokenEnSegundos * 1000));
        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(secreto.getBytes()), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", TOKEN_TYPE)
                .setSubject(Long.toString(user.getId()))
                .setIssuedAt(new Date())
                .setExpiration(tokenExpirationDate)
                .claim("username", user.getUsername())
                .claim("roles", user.getRoles().stream().map(UserRole::name).collect(Collectors.joining(", ")))
                .compact();
    }

    /**
     * Obtendremos el user id de un token
     *
     * @param token
     * @return
     */
    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(secreto.getBytes()))
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    /**
     * Valida un token y da información de las posibles excepciones
     *
     * @param authToken
     * @return
     */
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secreto.getBytes()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.info("Error en la firma del token: " + ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.info("Token mal formado: " + ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.info("El token ha expirado: " + ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.info("Token no soportado: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.info("Claims vacío");
        }
        return false;
    }
}
