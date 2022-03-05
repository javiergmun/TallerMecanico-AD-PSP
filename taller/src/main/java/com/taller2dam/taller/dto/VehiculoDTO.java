package com.taller2dam.taller.dto;

import com.taller2dam.taller.dao.Usuario;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehiculoDTO {

    private long id;
    @NotBlank(message = "Debes introducir una marca")
    private String marca;
    @NotBlank(message = "Debes introducir un modelo")
    private String modelo;
    @NotBlank(message = "Debes introducir una matrícula")
    private String matricula;
    @NotBlank(message = "Debes introducir un color")
    private String color;
    //@NotBlank(message = "Debes introducir un propietario")
    //private Usuario propietario;
    private String imagen;
    private String bitmap; //Para la imagen de android


}
