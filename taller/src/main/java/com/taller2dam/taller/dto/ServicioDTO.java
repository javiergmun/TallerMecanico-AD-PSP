package com.taller2dam.taller.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServicioDTO {
    private Long id;

    @Min(message = "El precio no puede ser negativo", value =0)
    private Double precio;

    @NotBlank(message = "Debes incluir un tipo")
    private String tipo;

    @NotBlank(message = "Debes fijar un tiempo de trabajo")
    private Double tiempo;
    private String imagen;
    private String bitmap; //Para la imagen de android
    private String descripcion;
/*
    @NotBlank(message = "Debes incluir una fecha de inicio")
    private LocalDateTime fecha_inicio;

    private LocalDateTime fecha_fin;

 */
}