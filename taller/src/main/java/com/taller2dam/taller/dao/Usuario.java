package com.taller2dam.taller.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.taller2dam.taller.dao.users.UserRole;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


//Con esto podríamos obtener la fecha  y hora de creación del usuario al usar el @CreateDate sobre un LocalDateTime
@EntityListeners(AuditingEntityListener.class)
@Builder
@Table(name = "usuario")
@NamedQuery(name = "usuario.findAll", query = "SELECT u FROM Usuario u")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic
    @Column(name = "correo")
    private String correo;

    @Basic
    @Column(name = "dni")
    private String dni;

    @Basic
    @Column(name = "nombre")
    private String username;

    @Basic
    @Column(name = "contraseña")
    private String password;    //Cifrarla o hacer que no se muestre

    @Basic
    @Column(name = "imagen")
    private String imagen;

    @Basic
    @Column(name = "telefono")
    private String telefono;

    @Basic
    @Column(name="direccion")
    private String direccion;
    /*
    @OneToOne
    @JoinColumn(name = "direccion", referencedColumnName = "id")
    private Direccion direccion;

     */
    @Basic
    @Column(name = "bitmap")
    private String bitmap; //Para la imagen de android


    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getBitmap() {
        return bitmap;
    }

    public void setBitmap(String bitmap) {
        this.bitmap = bitmap;
    }

    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "propietario", cascade = CascadeType.REMOVE)    //Ver Tipo de Cascada
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "propietario", cascade = CascadeType.REMOVE)
    private Set<Vehiculo> vehiculos;

    @OneToOne
    @JoinColumn(name = "login", referencedColumnName = "id")
    private Login login;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String phone) {
        this.telefono = phone;
    }

/*
    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion address) {
        this.direccion = address;
    }

 */

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String email) {
        this.correo = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Set<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(Set<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }


    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }


    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

///////////////////////////////////

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(rol -> new SimpleGrantedAuthority("ROLE_" + rol.name())).collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

}