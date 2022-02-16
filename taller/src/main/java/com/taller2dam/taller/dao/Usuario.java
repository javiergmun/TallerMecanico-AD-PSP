package com.taller2dam.taller.dao;

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

    private long id;
    private String dni;
    private String nombre;
    private Boolean administrador;
    private Integer telefono;
    private Direccion direccion;
    private Login login;
    private Set<Vehiculo> vehiculos;

    @Id
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

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

    @Basic
    @Column(name = "teléfono")
    public int getTelefono() {return telefono;}
    public void setTelefono(int phone) {this.telefono = phone;}

    @OneToOne
    @JoinColumn(name = "dirección", referencedColumnName = "id")
    public Direccion getDireccion() {return direccion;}
    public void setDireccion(Direccion address) {this.direccion = address;}

    @OneToOne
    @JoinColumn(name = "login", referencedColumnName = "id")
    public Login getLogin() {return login;}
    public void setLogin(Login login) {this.login = login;}

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "propietario", cascade = CascadeType.REMOVE)    //Ver Tipo de Cascada
    public Set<Vehiculo> getVehiculos() {return vehiculos;}
    public void setVehiculos(Set<Vehiculo> vehiculos) {this.vehiculos = vehiculos;}

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", administrador=" + administrador +
                ", telefono=" + telefono +
                ", direccion=" + direccion +
                ", login=" + login +
                ", vehiculos=" + vehiculos +
                '}';
    }
}
