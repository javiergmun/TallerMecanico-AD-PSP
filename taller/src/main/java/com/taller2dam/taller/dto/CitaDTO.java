package com.taller2dam.taller.dto;

import com.taller2dam.taller.dao.Mecanico;
import com.taller2dam.taller.dao.Servicio;
import com.taller2dam.taller.dao.Usuario;
import com.taller2dam.taller.dao.Vehiculo;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CitaDTO {


    private long id;
    /*@Min(message = "El precio no puede ser negativo", value = 0)
    private Double precio;

     */
    @NotBlank(message = "Debes introducir una fecha")
    private String fecha;
    @NotBlank(message = "Debes introducir un usuario")
    private Usuario usuario;
    @NotBlank(message = "Debes introducir un mecánico")
    private Mecanico mecanico;
    @NotBlank(message = "Debes introducir un servicio")
    private Servicio servicio;
    @NotBlank(message = "Debes introducir un vehiculo")
    private Vehiculo vehiculo;

}