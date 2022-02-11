package com.taller2dam.dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
@NamedQuery(name = "usuario.findAll", query = "SELECT u FROM Usuario u")
public class Usuario {

    private UUID id;
    private String dni;
    private String nombre;
    private Boolean administrador;
    private Login login;
    private Set<Vehiculo> vehiculos;

    @Id
    public UUID getId() {return id;}
    public void setId(UUID id) {this.id = id;}

    @Basic
    @Column(name = "dni")
    public String getDni() {return dni;}
    public void setDni(String dni) {this.dni = dni;}

    @Basic
    @Column(name = "nombre")
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    @Basic
    @Column(name = "es_administrador")
    public Boolean getAdministrador() {return administrador;}
    public void setAdministrador(Boolean administrador) {this.administrador = administrador;}

    @OneToOne
    @JoinColumn(name = "login", referencedColumnName = "id")
    public Login getLogin() {return login;}
    public void setLogin(Login login) {this.login = login;}

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "vehiculo", cascade = CascadeType.REMOVE)    //Ver Tipo de Cascada
    public Set<Vehiculo> getVehicolos() {return vehiculos;}
    public void setVehicolos(Set<Vehiculo> vehicolos) {this.vehiculos = vehicolos;}

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", administrador=" + administrador +
                ", login=" + login +
                ", vehiculos=" + vehiculos +
                '}';
    }
}
