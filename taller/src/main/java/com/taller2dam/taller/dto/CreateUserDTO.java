package com.taller2dam.taller.dto;


import com.taller2dam.taller.dao.users.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {
    private String username;
    private String password;


    private String dni;
    private String telefono;

    //Esto lo dará por campos y lo montaremos
    //private Direccion direccion;

    private String correo;
    private Set<UserRole> roles;
}
