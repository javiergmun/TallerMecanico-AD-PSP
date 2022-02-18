package com.taller2dam.taller.dto;

import com.taller2dam.taller.dao.Servicio;
import lombok.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MecanicoDTO {
    private Long id;

    @NotBlank(message = "Necesitas tener un nombre")
    private String nombre;

    @Min(message = "El salario no puede ser negativo", value = 0)
    private Double salario;

    private Servicio servicio;

}