package com.taller2dam.taller.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "mecanico")
@NamedQuery(name = "mecanico.findAll", query = "SELECT m FROM Mecanico m")
public class Mecanico {

    private long id;
    private String nombre;
    private Double salario;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    @Basic
    @Column(name = "username")
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    @Basic
    @Column(name = "salario")
    public Double getSalario() {return salario;}
    public void setSalario(Double salario) {this.salario = salario;}


    @Override
    public String toString() {
        return "Mecanico{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", salario=" + salario +
                '}';
    }
}