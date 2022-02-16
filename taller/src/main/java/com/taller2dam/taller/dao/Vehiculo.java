package com.taller2dam.taller.dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vehiculo")
@NamedQuery(name = "vehiculo.findAll", query = "SELECT v FROM Vehiculo v")
public class Vehiculo {

    private long id;
    private String fallos;
    private String marca;
    private String modelo;
    private String matricula;
    private String color;
    private Usuario propietario;

    @Id
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    @Basic
    @Column(name = "fallos")
    public String getFallos() {return fallos;}
    public void setFallos(String fallos) {this.fallos = fallos;}

    @Basic
    @Column(name = "marca")
    public String getMarca() {return marca;}
    public void setMarca(String marca) {this.marca = marca;}

    @Basic
    @Column(name = "modelo")
    public String getModelo() {return modelo;}
    public void setModelo(String modelo) {this.modelo = modelo;}

    @Basic
    @Column(name = "matricula")
    public String getMatricula() {return matricula;}
    public void setMatricula(String matricula) {this.matricula = matricula;}

    @Basic
    @Column(name = "color")
    public String getColor() {return color;}
    public void setColor(String color) {this.color = color;}

    @ManyToOne
    @JoinColumn(name = "propietario", referencedColumnName = "id")
    public Usuario getPropietario() {return propietario;}
    public void setPropietario(Usuario propietario) {this.propietario = propietario;}

    @Override
    public String toString() {
        return "Vehiculo{" +
                "id=" + id +
                ", fallos='" + fallos + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", matricula='" + matricula + '\'' +
                ", color='" + color + '\'' +
                ", propietario=" + propietario +
                '}';
    }
}
