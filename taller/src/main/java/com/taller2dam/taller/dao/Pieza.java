package com.taller2dam.taller.dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "piezas")
@NamedQuery(name = "piezas.findAll", query = "SELECT p FROM Pieza p")
public class Pieza {

    private long id;
    private String nombre;
    private Integer stock;
    private Double precio;
    private Servicio servicio;

    @Id
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    @Basic
    @Column(name = "nombre")
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    @Basic
    @Column(name = "stock")
    public Integer getStock() {return stock;}
    public void setStock(Integer stock) {this.stock = stock;}

    @Basic
    @Column(name = "precio")
    public Double getPrecio() {return precio;}
    public void setPrecio(Double precio) {this.precio = precio;}

    @ManyToOne
    @JoinColumn(name = "servicio", referencedColumnName = "id")
    public Servicio getServicio() {return servicio;}
    public void setServicio(Servicio servicio) {this.servicio = servicio;}

    @Override
    public String toString() {
        return "Pieza{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", stock=" + stock +
                ", precio=" + precio +
                '}';
    }
}
