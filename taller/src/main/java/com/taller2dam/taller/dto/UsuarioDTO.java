package com.taller2dam.taller.dto;

import com.taller2dam.taller.dao.Direccion;
import com.taller2dam.taller.dao.Vehiculo;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private long id;
    @NotBlank(message = "Debes introducir un dni")
    private String dni;
    @NotBlank(message = "Debes introducir un nombre")
    private String nombre;
    @NotBlank(message = "Debes indicar si es un administrador")
    private Boolean administrador;
    @NotBlank(message = "Debes introducir un telefono")
    private String telefono;
    @NotBlank(message = "Debes introducir una dirección")
    private Direccion direccion;
    //Esta lista si puede estar vacía. Un usuario puede no tener aun ningun vahículo.
    //Se hará el registro del vehículo en el layout correspondiente.
    private Set<Vehiculo> vehiculos;

}