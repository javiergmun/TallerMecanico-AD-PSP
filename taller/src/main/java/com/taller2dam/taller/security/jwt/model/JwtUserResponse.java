package com.taller2dam.taller.security.jwt.model;

import com.taller2dam.taller.dao.Direccion;
import com.taller2dam.taller.dao.Vehiculo;
import com.taller2dam.taller.dto.UsuarioDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class JwtUserResponse extends UsuarioDTO {
    private String token;

    @Builder(builderMethodName = "jwtUserResponseBuilder")
    public JwtUserResponse(long id, @NotBlank(message = "Debes introducir un dni") String dni, @NotBlank(message = "Debes introducir un nombre") String username, @NotBlank(message = "Debes introducir un telefono") String telefono, @NotBlank(message = "Debes introducir un correo") String correo, @NotBlank(message = "Debes introducir una dirección") Direccion direccion, Set<Vehiculo> vehiculos, @NotBlank(message = "Debes introducir una pass") String password, String imagen, String bitmap, Set<String> roles, String token) {
        super(id, dni, username, telefono, correo, direccion, vehiculos, password, imagen, bitmap, roles);
        this.token = token;
    }
}
