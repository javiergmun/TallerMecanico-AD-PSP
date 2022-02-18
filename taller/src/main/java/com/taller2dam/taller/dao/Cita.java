package com.taller2dam.taller.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cita")
@NamedQuery(name = "cita.findAll", query = "SELECT c FROM Cita c")
public class Cita {

    private long id;
    private Double precio;
    private LocalDateTime fecha;
    private Usuario usuario;
    private Mecanico mecanico;
    private Servicio servicio;

    @Id
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    @Basic
    @Column(name = "precio")
    public Double getPrecio() {return precio;}
    public void setPrecio(Double precio) {this.precio = precio;}

    @Basic
    @CreationTimestamp
    @Column(name = "fecha")
    public LocalDateTime getFecha() {return fecha;}
    public void setFecha(LocalDateTime fecha) {this.fecha = fecha;}

    @OneToOne
    @JoinColumn(name = "cliente", referencedColumnName = "id")
    public Usuario getUsuario() {return usuario;}
    public void setUsuario(Usuario usuario) {this.usuario = usuario;}

    @OneToOne
    @JoinColumn(name = "mecanico", referencedColumnName = "id")
    public Mecanico getMecanico() {return mecanico;}
    public void setMecanico(Mecanico mecanico) {this.mecanico = mecanico;}

    @OneToOne
    @JoinColumn(name = "servicio", referencedColumnName = "id")
    public Servicio getServicio() {return servicio;}
    public void setServicio(Servicio servicio) {this.servicio = servicio;}

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", precio=" + precio +
                ", fecha=" + fecha +
                ", usuario=" + usuario +
                ", mecanico=" + mecanico +
                ", servicio=" + servicio +
                '}';
    }
}
