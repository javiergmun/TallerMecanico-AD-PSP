package com.taller2dam.taller.dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mecánico")
@NamedQuery(name = "mecánico.findAll", query = "SELECT m FROM Mecanico m")
public class Mecanico {

    private UUID id;
    private String nombre;
    private Double salario;
    private Servicio servicio;

    @Id
    public UUID getId() {return id;}
    public void setId(UUID id) {this.id = id;}

    @Basic
    @Column(name = "nombre")
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    @Basic
    @Column(name = "salario")
    public Double getSalario() {return salario;}
    public void setSalario(Double salario) {this.salario = salario;}

    @OneToOne
    @JoinColumn(name = "servicio_asignado", referencedColumnName = "id")
    public Servicio getServicio() {return servicio;}
    public void setServicio(Servicio servicio) {this.servicio = servicio;}

    @Override
    public String toString() {
        return "Mecanico{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", salario=" + salario +
                ", servicio=" + servicio +
                '}';
    }
}
