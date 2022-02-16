package com.taller2dam.taller.dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "servicio")
@NamedQuery(name = "servicio.findAll", query = "SELECT s FROM Servicio s")
public class Servicio {

    private UUID id;
    private Double precio;
    private String tipo;
    private Double tiempo;
    private LocalDateTime fecha_inicio;
    private LocalDateTime fecha_fin;
    private Set<Pieza> repuestos;   //recambios o materiales de nuestro taller (sino gusta se puede quitar)

    @Id
    public UUID getId() {return id;}
    public void setId(UUID id) {this.id = id;}

    @Basic
    @Column(name = "precio")
    public Double getPrecio() {return precio;}
    public void setPrecio(Double precio) {this.precio = precio;}

    @Basic
    @Column(name = "tipo")
    public String getTipo() {return tipo;}
    public void setTipo(String tipo) {this.tipo = tipo;}

    @Basic
    @Column(name = "tiempo")
    public Double getTiempo() {return tiempo;}
    public void setTiempo(Double tiempo) {this.tiempo = tiempo;}

    @Basic
    @CreationTimestamp
    @Column(name = "fecha_inico")
    public LocalDateTime getFecha_inicio() {return fecha_inicio;}
    public void setFecha_inicio(LocalDateTime fecha_inicio) {this.fecha_inicio = fecha_inicio;}

    @Basic
    @CreationTimestamp
    @Column(name = "fecha_fin")
    public LocalDateTime getFecha_fin() {return fecha_fin;}
    public void setFecha_fin(LocalDateTime fecha_fin) {this.fecha_fin = fecha_fin;}

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "servicio", cascade = CascadeType.REMOVE)    //Ver Tipo de Cascada
    public Set<Pieza> getRepuestos() {return repuestos;}
    public void setRepuestos(Set<Pieza> repuestos) {this.repuestos = repuestos;}

    @Override
    public String toString() {
        return "Servicio{" +
                "id=" + id +
                ", precio=" + precio +
                ", tipo='" + tipo + '\'' +
                ", tiempo=" + tiempo +
                ", fecha_inicio=" + fecha_inicio +
                ", fecha_fin=" + fecha_fin +
                ", repuestos=" + repuestos +
                '}';
    }
}