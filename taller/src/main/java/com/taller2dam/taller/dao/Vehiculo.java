package com.taller2dam.taller.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "vehiculo")
@NamedQuery(name = "vehiculo.findAll", query = "SELECT v FROM Vehiculo v")
public class Vehiculo {

    private long id;
    private String marca;
    private String modelo;
    private String matricula;
    private String color;
    //private Usuario propietario;
    private String imagen;

    @Id
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "marca")
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Basic
    @Column(name = "modelo")
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Basic
    @Column(name = "matricula")
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Basic
    @Column(name = "color")
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
/*
    @ManyToOne
    @JoinColumn(name = "propietario", referencedColumnName = "id")
    public Usuario getPropietario() {
        return propietario;
    }
    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

 */

    @Basic
    @Column(name = "imagen")
    public String getImagen() {return imagen;}
    public void setImagen(String imagen) {this.imagen = imagen;}

    @Override
    public String toString() {
        return "Vehiculo{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", matricula='" + matricula + '\'' +
                ", color='" + color + '\'' +
                //", propietario=" + propietario +
                '}';
    }
}
