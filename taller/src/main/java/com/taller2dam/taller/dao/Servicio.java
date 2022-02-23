package com.taller2dam.taller.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "servicio")
@NamedQuery(name = "servicio.findAll", query = "SELECT s FROM Servicio s")
public class Servicio {

    private long id;
    private Double precio;
    private String tipo;
    private Double tiempo;
    private LocalDateTime fecha_inicio;
    private LocalDateTime fecha_fin;

    @Id
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "precio")
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Basic
    @Column(name = "tipo")
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Basic
    @Column(name = "tiempo")
    public Double getTiempo() {
        return tiempo;
    }
    public void setTiempo(Double tiempo) {
        this.tiempo = tiempo;
    }

    @Basic
    @CreationTimestamp
    @Column(name = "fecha_inicio")
    public LocalDateTime getFecha_inicio() {
        return fecha_inicio;
    }
    public void setFecha_inicio(LocalDateTime fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    @Basic
    @CreationTimestamp
    @Column(name = "fecha_fin")
    public LocalDateTime getFecha_fin() {
        return fecha_fin;
    }
    public void setFecha_fin(LocalDateTime fecha_fin) {
        this.fecha_fin = fecha_fin;
    }


    @Override
    public String toString() {
        return "Servicio{" +
                "id=" + id +
                ", precio=" + precio +
                ", tipo='" + tipo + '\'' +
                ", tiempo=" + tiempo +
                ", fecha_inicio=" + fecha_inicio +
                ", fecha_fin=" + fecha_fin +
                '}';
    }
}