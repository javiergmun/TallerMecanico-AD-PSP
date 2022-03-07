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
    //private Double precio;
    private LocalDateTime fecha;
    private Usuario usuario;
    private Mecanico mecanico;
    private Servicio servicio;
    private Vehiculo vehiculo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @CreationTimestamp
    @Column(name = "fecha")
    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    @OneToOne
    @JoinColumn(name = "cliente", referencedColumnName = "id")
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @OneToOne
    @JoinColumn(name = "mecanico", referencedColumnName = "id")
    public Mecanico getMecanico() {
        return mecanico;
    }

    public void setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }

    @OneToOne
    @JoinColumn(name = "servicio_contratado", referencedColumnName = "id")
    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    @OneToOne
    @JoinColumn(name = "vehiculo_del_usuario", referencedColumnName = "id")
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", usuario=" + usuario +
                ", mecanico=" + mecanico +
                ", servicio=" + servicio +
                '}';
    }
}