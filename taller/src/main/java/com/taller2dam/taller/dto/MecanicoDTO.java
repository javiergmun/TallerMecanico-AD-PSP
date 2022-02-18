package com.taller2dam.taller.dto;

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

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

}