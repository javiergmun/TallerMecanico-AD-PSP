package com.taller2dam.taller.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "usuario")
@NamedQuery(name = "usuario.findAll", query = "SELECT u FROM Usuario u")
public class Usuario {

    private long id;
    private String dni;
    private String nombre;
    private Boolean administrador;
    private Integer telefono;
    private Direccion direccion;
    private String correo;
    private String password;    //Cifrarla o hacer que no se muestre
    private Set<Vehiculo> vehiculos;
    private Login login;

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
    @Column(name = "telefono")
    public int getTelefono() {return telefono;}
    public void setTelefono(int phone) {this.telefono = phone;}

    @OneToOne
    @JoinColumn(name = "direccion", referencedColumnName = "id")
    public Direccion getDireccion() {return direccion;}
    public void setDireccion(Direccion address) {this.direccion = address;}

    @Basic
    @Column(name = "correo")
    public String getCorreo() {return correo;}
    public void setCorreo(String email) {this.correo = email;}

    @Basic
    @Column(name = "contraseña")
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "propietario", cascade = CascadeType.REMOVE)    //Ver Tipo de Cascada
    @OneToMany
    public Set<Vehiculo> getVehiculos() {return vehiculos;}
    public void setVehiculos(Set<Vehiculo> vehiculos) {this.vehiculos = vehiculos;}

    @OneToOne
    @JoinColumn(name = "login", referencedColumnName = "id")
    public Login getLogin() {return login;}
    public void setLogin(Login login) {this.login = login;}

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", administrador=" + administrador +
                ", telefono=" + telefono +
                ", direccion=" + direccion +
                ", correo='" + correo + '\'' +
                ", password='" + password + '\'' +
                ", vehiculos=" + vehiculos +
                ", login="+ login +
                '}';
    }
}
