package com.taller2dam.taller.dto;

import com.taller2dam.taller.dao.Servicio;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    private Long id;

    //@Email(regexp = ".*@.*\\..*", message = "Email debe ser correcto")
    //private String correo;

    //@NotBlank(message = "Debes incluir una contraseña")
    //private String password;

    private String token;

}